package net.mooplemax.madmachines.block.entity;


import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import net.mooplemax.madmachines.block.custom.SuperHopperT8Block;
import net.mooplemax.madmachines.screen.SuperHopperScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SuperHopperT8BlockEntity
        extends LootableContainerBlockEntity
        implements Hopper {
    public static final int field_31341 = 8;
    public static final int field_31342 = 10;

    private int actualCooldown = 1; //Changes how many ticks between item transfers
    private int transferStackSize = 1; //Changes how many items are extracted or inserted per transfer

    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(14, ItemStack.EMPTY);
    private int transferCooldown = -1;
    private long lastTickTime;



    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){

        @Override
        public int get(int index) {
            return 0;
        }

        @Override
        public void set(int index, int value) {

        }

        @Override
        public int size() {
            return 0;
        }
    };

    public SuperHopperT8BlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SUPER_HOPPER_T8, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.inventory.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory);
        }
        this.transferCooldown = nbt.getInt("TransferCooldown");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }
        nbt.putInt("TransferCooldown", this.transferCooldown);
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        this.checkLootInteraction(null);
        return Inventories.splitStack(this.getInvStackList(), slot, amount);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.checkLootInteraction(null);
        this.getInvStackList().set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.super_hopper_t8");
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, SuperHopperT8BlockEntity blockEntity) {
        --blockEntity.transferCooldown;
        blockEntity.lastTickTime = world.getTime();
        if (!blockEntity.needsCooldown()) {
            blockEntity.setTransferCooldown(0);
            SuperHopperT8BlockEntity.insertAndExtract(world, pos, state, blockEntity, () -> SuperHopperT8BlockEntity.extract(world, blockEntity, blockEntity.transferStackSize));
        }
    }

    private static boolean insertAndExtract(World world, BlockPos pos, BlockState state, SuperHopperT8BlockEntity blockEntity, BooleanSupplier booleanSupplier) {
        if (world.isClient) {
            return false;
        }
        if (!blockEntity.needsCooldown() && state.get(SuperHopperT8Block.ENABLED).booleanValue()) {
            boolean bl = false;
            if (!blockEntity.isEmpty()) {
                bl = SuperHopperT8BlockEntity.insert(world, pos, state, blockEntity, blockEntity.transferStackSize);
            }
            if (!blockEntity.isFull()) {
                bl |= booleanSupplier.getAsBoolean();
            }
            if (bl) {
                blockEntity.setTransferCooldown(blockEntity.actualCooldown); //also trasnfer cooldown
                SuperHopperT8BlockEntity.markDirty(world, pos, state);
                return true;
            }
        }
        return false;
    }

    private boolean isFull() {
        for (ItemStack itemStack : this.inventory) {
            if (!itemStack.isEmpty() && itemStack.getCount() == itemStack.getMaxCount()) continue;
            return false;
        }
        return true;
    }

    private static boolean insert(World world, BlockPos pos, BlockState state, Inventory inventory, int amount) {
        Inventory inventory2 = SuperHopperT8BlockEntity.getOutputInventory(world, pos, state);
        if (inventory2 == null) {
            return false;
        }
        Direction direction = state.get(SuperHopperT8Block.FACING).getOpposite();
        if (SuperHopperT8BlockEntity.isInventoryFull(inventory2, direction)) {
            return false;
        }
        for (int i = 0; i < inventory.size(); ++i) {
            if (inventory.getStack(i).isEmpty()) continue;
            ItemStack itemStack = inventory.getStack(i).copy();
            ItemStack itemStack2 = SuperHopperT8BlockEntity.transfer(inventory, inventory2, inventory.removeStack(i, amount), direction); //item push quantity
            if (itemStack2.isEmpty()) {
                inventory2.markDirty();
                return true;
            }
            inventory.setStack(i, itemStack);
        }
        return false;
    }

    private static IntStream getAvailableSlots(Inventory inventory, Direction side) {
        if (inventory instanceof SidedInventory) {
            return IntStream.of(((SidedInventory)inventory).getAvailableSlots(side));
        }
        return IntStream.range(0, inventory.size());
    }

    private static boolean isInventoryFull(Inventory inventory, Direction direction) {
        return SuperHopperT8BlockEntity.getAvailableSlots(inventory, direction).allMatch(slot -> {
            ItemStack itemStack = inventory.getStack(slot);
            return itemStack.getCount() >= itemStack.getMaxCount();
        });
    }

    private static boolean isInventoryEmpty(Inventory inv, Direction facing) {
        return SuperHopperT8BlockEntity.getAvailableSlots(inv, facing).allMatch(slot -> inv.getStack(slot).isEmpty());
    }

    public static boolean extract(World world, Hopper hopper, int amount) {
        Inventory inventory = SuperHopperT8BlockEntity.getInputInventory(world, hopper);
        if (inventory != null) {
            Direction direction = Direction.DOWN;
            if (SuperHopperT8BlockEntity.isInventoryEmpty(inventory, direction)) {
                return false;
            }
            return SuperHopperT8BlockEntity.getAvailableSlots(inventory, direction).anyMatch(slot -> SuperHopperT8BlockEntity.extract(hopper, inventory, slot, direction, amount));
        }
        for (ItemEntity itemEntity : SuperHopperT8BlockEntity.getInputItemEntities(world, hopper)) {
            if (!SuperHopperT8BlockEntity.extract(hopper, itemEntity)) continue;
            return true;
        }
        return false;
    }

    private static boolean extract(Hopper hopper, Inventory inventory, int slot, Direction side, int amount) {
        if (!(slot >= 10)) {
            ItemStack itemStack = inventory.getStack(slot);
            if (!itemStack.isEmpty() && SuperHopperT8BlockEntity.canExtract(inventory, itemStack, slot, side)) {
                ItemStack itemStack2 = itemStack.copy();
                ItemStack itemStack3 = SuperHopperT8BlockEntity.transfer(inventory, hopper, inventory.removeStack(slot, amount), null); //item extraction quantity
                if (itemStack3.isEmpty()) {
                    inventory.markDirty();
                    return true;
                }
                inventory.setStack(slot, itemStack2);
            }
        }
        return false;
    }

    public static boolean extract(Inventory inventory, ItemEntity itemEntity) {
        boolean bl = false;
        ItemStack itemStack = itemEntity.getStack().copy();
        ItemStack itemStack2 = SuperHopperT8BlockEntity.transfer(null, inventory, itemStack, null);
        if (itemStack2.isEmpty()) {
            bl = true;
            itemEntity.discard();
        } else {
            itemEntity.setStack(itemStack2);
        }
        return bl;
    }

    public static ItemStack transfer(@Nullable Inventory from, Inventory to, ItemStack stack, @Nullable Direction side) {
        if (to instanceof SidedInventory && side != null) {
            SidedInventory sidedInventory = (SidedInventory)to;
            int[] is = sidedInventory.getAvailableSlots(side);
            for (int i = 0; i < is.length && !stack.isEmpty(); ++i) {
                stack = SuperHopperT8BlockEntity.transfer(from, to, stack, is[i], side);
            }
        } else {
            int j = to.size();
            for (int k = 0; k < j && !stack.isEmpty(); ++k) {
                stack = SuperHopperT8BlockEntity.transfer(from, to, stack, k, side);
            }
        }
        return stack;
    }

    private static boolean canInsert(Inventory inventory, ItemStack stack, int slot, @Nullable Direction side) {
        if (!inventory.isValid(slot, stack)) {
            return false;
        }
        return !(inventory instanceof SidedInventory) || ((SidedInventory)inventory).canInsert(slot, stack, side);
    }

    private static boolean canExtract(Inventory inv, ItemStack stack, int slot, Direction facing) {
        if (!inv.isValid(slot, stack)) {
            return false;
        }
        return !(inv instanceof SidedInventory) || ((SidedInventory)inv).canExtract(slot, stack, facing);
    }

    private static ItemStack transfer(@Nullable Inventory from, Inventory to, ItemStack stack, int slot, @Nullable Direction side) {
        ItemStack itemStack = to.getStack(slot);
        if (SuperHopperT8BlockEntity.canInsert(to, stack, slot, side)) {
            int j;
            boolean bl = false;
            boolean bl2 = to.isEmpty();
            if (itemStack.isEmpty()) {
                to.setStack(slot, stack);
                stack = ItemStack.EMPTY;
                bl = true;
            } else if (SuperHopperT8BlockEntity.canMergeItems(itemStack, stack)) {
                int i = stack.getMaxCount() - itemStack.getCount();
                j = Math.min(stack.getCount(), i);
                stack.decrement(j);
                itemStack.increment(j);
                boolean bl3 = bl = j > 0;
            }
            if (bl) {
                SuperHopperT8BlockEntity superHopperT8BlockEntity;
                if (bl2 && to  instanceof SuperHopperT8BlockEntity && !(superHopperT8BlockEntity = (SuperHopperT8BlockEntity)to).isDisabled()) {
                    j = 0;
                    if (from instanceof SuperHopperT8BlockEntity) {
                        SuperHopperT8BlockEntity superHopperT2BlockEntity8 = (SuperHopperT8BlockEntity)from;
                        if (superHopperT8BlockEntity.lastTickTime >= superHopperT2BlockEntity8.lastTickTime) {
                            j = 1;
                        }
                    }
                    superHopperT8BlockEntity.setTransferCooldown(superHopperT8BlockEntity.actualCooldown - j); //Tick Speed
                }
                to.markDirty();
            }
        }
        return stack;
    }

    @Nullable
    private static Inventory getOutputInventory(World world, BlockPos pos, BlockState state) {
        Direction direction = state.get(SuperHopperT8Block.FACING);
        return SuperHopperT8BlockEntity.getInventoryAt(world, pos.offset(direction));
    }

    @Nullable
    private static Inventory getInputInventory(World world, Hopper hopper) {
        return SuperHopperT8BlockEntity.getInventoryAt(world, hopper.getHopperX(), hopper.getHopperY() + 1.0, hopper.getHopperZ());
    }

    public static List<ItemEntity> getInputItemEntities(World world, Hopper hopper) {
        return hopper.getInputAreaShape().getBoundingBoxes().stream().flatMap(box -> world.getEntitiesByClass(ItemEntity.class, box.offset(hopper.getHopperX() - 0.5, hopper.getHopperY() - 0.5, hopper.getHopperZ() - 0.5), EntityPredicates.VALID_ENTITY).stream()).collect(Collectors.toList());
    }

    @Nullable
    public static Inventory getInventoryAt(World world, BlockPos pos) {
        return SuperHopperT8BlockEntity.getInventoryAt(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
    }

    @Nullable
    private static Inventory getInventoryAt(World world, double x, double y, double z) {
        List<Entity> list;
        BlockEntity blockEntity;
        Inventory inventory = null;
        BlockPos blockPos = new BlockPos(x, y, z);
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if (block instanceof InventoryProvider) {
            inventory = ((InventoryProvider)((Object)block)).getInventory(blockState, world, blockPos);
        } else if (blockState.hasBlockEntity() && (blockEntity = world.getBlockEntity(blockPos)) instanceof Inventory && (inventory = (Inventory)((Object)blockEntity)) instanceof ChestBlockEntity && block instanceof ChestBlock) {
            inventory = ChestBlock.getInventory((ChestBlock)block, blockState, world, blockPos, true);
        }
        if (inventory == null && !(list = world.getOtherEntities(null, new Box(x - 0.5, y - 0.5, z - 0.5, x + 0.5, y + 0.5, z + 0.5), EntityPredicates.VALID_INVENTORIES)).isEmpty()) {
            inventory = (Inventory)((Object)list.get(world.random.nextInt(list.size())));
        }
        return inventory;
    }

    private static boolean canMergeItems(ItemStack first, ItemStack second) {
        if (!first.isOf(second.getItem())) {
            return false;
        }
        if (first.getDamage() != second.getDamage()) {
            return false;
        }
        if (first.getCount() > first.getMaxCount()) {
            return false;
        }
        return ItemStack.areNbtEqual(first, second);
    }

    @Override
    public double getHopperX() {
        return (double)this.pos.getX() + 0.5;
    }

    @Override
    public double getHopperY() {
        return (double)this.pos.getY() + 0.5;
    }

    @Override
    public double getHopperZ() {
        return (double)this.pos.getZ() + 0.5;
    }

    private void setTransferCooldown(int transferCooldown) {
        this.transferCooldown = transferCooldown;
    }

    private boolean needsCooldown() {
        return this.transferCooldown > 0;
    }

    private boolean isDisabled() {
        return this.transferCooldown > 8;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    public static void onEntityCollided(World world, BlockPos pos, BlockState state, Entity entity, SuperHopperT8BlockEntity blockEntity) {
        if (entity instanceof ItemEntity && VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entity.getBoundingBox().offset(-pos.getX(), -pos.getY(), -pos.getZ())), blockEntity.getInputAreaShape(), BooleanBiFunction.AND)) {
            SuperHopperT8BlockEntity.insertAndExtract(world, pos, state, blockEntity, () -> SuperHopperT8BlockEntity.extract(blockEntity, (ItemEntity)entity));
        }
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new SuperHopperScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}