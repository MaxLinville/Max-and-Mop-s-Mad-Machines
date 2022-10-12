package net.mooplemax.madmachines.block.entity;


import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import net.mooplemax.madmachines.block.custom.SuperHopperT15Block;
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

public class SuperHopperT15BlockEntity
        extends LootableContainerBlockEntity
        implements Hopper {
    public static final int field_31341 = 8;
    public static final int field_31342 = 10;

    private int actualCooldown = 1; //Changes how many ticks between item transfers
    private int transferStackSize = 64; //Changes how many items are extracted or inserted per transfer

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

    public SuperHopperT15BlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SUPER_HOPPER_T15, pos, state);
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
        return Text.translatable("container.super_hopper_t15");
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, SuperHopperT15BlockEntity blockEntity) {
        --blockEntity.transferCooldown;
        blockEntity.lastTickTime = world.getTime();
        if (!blockEntity.needsCooldown()) {
            blockEntity.setTransferCooldown(0);
            SuperHopperT15BlockEntity.insertAndExtract(world, pos, state, blockEntity, () -> SuperHopperT15BlockEntity.extract(world, blockEntity, blockEntity.transferStackSize));
        }
    }

    private static boolean insertAndExtract(World world, BlockPos pos, BlockState state, SuperHopperT15BlockEntity blockEntity, BooleanSupplier booleanSupplier) {
        if (world.isClient) {
            return false;
        }
        if (!blockEntity.needsCooldown() && state.get(SuperHopperT15Block.ENABLED).booleanValue()) {
            boolean bl = false;
            if (!blockEntity.isEmpty()) {
                bl = SuperHopperT15BlockEntity.insert(world, pos, state, blockEntity, blockEntity.transferStackSize);
            }
            if (!blockEntity.isFull()) {
                bl |= booleanSupplier.getAsBoolean();
            }
            if (bl) {
                blockEntity.setTransferCooldown(blockEntity.actualCooldown); //also trasnfer cooldown
                SuperHopperT15BlockEntity.markDirty(world, pos, state);
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
        Inventory inventory2 = SuperHopperT15BlockEntity.getOutputInventory(world, pos, state);
        if (inventory2 == null) {
            return false;
        }
        Direction direction = state.get(SuperHopperT15Block.FACING).getOpposite();
        if (SuperHopperT15BlockEntity.isInventoryFull(inventory2, direction)) {
            return false;
        }
        for (int i = 0; i < inventory.size(); ++i) {
            if (inventory.getStack(i).isEmpty()) continue;
            ItemStack itemStack = inventory.getStack(i).copy();
            ItemStack itemStack2 = SuperHopperT15BlockEntity.transfer(inventory, inventory2, inventory.removeStack(i, amount), direction); //item push quantity
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
        return SuperHopperT15BlockEntity.getAvailableSlots(inventory, direction).allMatch(slot -> {
            ItemStack itemStack = inventory.getStack(slot);
            return itemStack.getCount() >= itemStack.getMaxCount();
        });
    }

    private static boolean isInventoryEmpty(Inventory inv, Direction facing) {
        return SuperHopperT15BlockEntity.getAvailableSlots(inv, facing).allMatch(slot -> inv.getStack(slot).isEmpty());
    }

    public static boolean extract(World world, Hopper hopper, int amount) {
        Inventory inventory = SuperHopperT15BlockEntity.getInputInventory(world, hopper);
        if (inventory != null) {
            Direction direction = Direction.DOWN;
            if (SuperHopperT15BlockEntity.isInventoryEmpty(inventory, direction)) {
                return false;
            }
            return SuperHopperT15BlockEntity.getAvailableSlots(inventory, direction).anyMatch(slot -> SuperHopperT15BlockEntity.extract(hopper, inventory, slot, direction, amount));
        }
        for (ItemEntity itemEntity : SuperHopperT15BlockEntity.getInputItemEntities(world, hopper)) {
            if (!SuperHopperT15BlockEntity.extract(hopper, itemEntity)) continue;
            return true;
        }
        return false;
    }

    private static boolean extract(Hopper hopper, Inventory inventory, int slot, Direction side, int amount) {
        if (!(slot >= 10)) {
            ItemStack itemStack = inventory.getStack(slot);
            if (!itemStack.isEmpty() && SuperHopperT15BlockEntity.canExtract(inventory, itemStack, slot, side)) {
                ItemStack itemStack2 = itemStack.copy();
                ItemStack itemStack3 = SuperHopperT15BlockEntity.transfer(inventory, hopper, inventory.removeStack(slot, amount), null); //item extraction quantity
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
        ItemStack itemStack2 = SuperHopperT15BlockEntity.transfer(null, inventory, itemStack, null);
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
                stack = SuperHopperT15BlockEntity.transfer(from, to, stack, is[i], side);
            }
        } else {
            int j = to.size();
            for (int k = 0; k < j && !stack.isEmpty(); ++k) {
                stack = SuperHopperT15BlockEntity.transfer(from, to, stack, k, side);
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
        if (SuperHopperT15BlockEntity.canInsert(to, stack, slot, side)) {
            int j;
            boolean bl = false;
            boolean bl2 = to.isEmpty();
            if (itemStack.isEmpty()) {
                to.setStack(slot, stack);
                stack = ItemStack.EMPTY;
                bl = true;
            } else if (SuperHopperT15BlockEntity.canMergeItems(itemStack, stack)) {
                int i = stack.getMaxCount() - itemStack.getCount();
                j = Math.min(stack.getCount(), i);
                stack.decrement(j);
                itemStack.increment(j);
                boolean bl3 = bl = j > 0;
            }
            if (bl) {
                SuperHopperT15BlockEntity superHopperT15BlockEntity;
                if (bl2 && to  instanceof SuperHopperT15BlockEntity && !(superHopperT15BlockEntity = (SuperHopperT15BlockEntity)to).isDisabled()) {
                    j = 0;
                    if (from instanceof SuperHopperT15BlockEntity) {
                        SuperHopperT15BlockEntity superHopperT2BlockEntity15 = (SuperHopperT15BlockEntity)from;
                        if (superHopperT15BlockEntity.lastTickTime >= superHopperT2BlockEntity15.lastTickTime) {
                            j = 1;
                        }
                    }
                    superHopperT15BlockEntity.setTransferCooldown(superHopperT15BlockEntity.actualCooldown - j); //Tick Speed
                }
                to.markDirty();
            }
        }
        return stack;
    }

    @Nullable
    private static Inventory getOutputInventory(World world, BlockPos pos, BlockState state) {
        Direction direction = state.get(SuperHopperT15Block.FACING);
        return SuperHopperT15BlockEntity.getInventoryAt(world, pos.offset(direction));
    }

    @Nullable
    private static Inventory getInputInventory(World world, Hopper hopper) {
        return SuperHopperT15BlockEntity.getInventoryAt(world, hopper.getHopperX(), hopper.getHopperY() + 1.0, hopper.getHopperZ());
    }

    public static List<ItemEntity> getInputItemEntities(World world, Hopper hopper) {
        return hopper.getInputAreaShape().getBoundingBoxes().stream().flatMap(box -> world.getEntitiesByClass(ItemEntity.class, box.offset(hopper.getHopperX() - 0.5, hopper.getHopperY() - 0.5, hopper.getHopperZ() - 0.5), EntityPredicates.VALID_ENTITY).stream()).collect(Collectors.toList());
    }

    @Nullable
    public static Inventory getInventoryAt(World world, BlockPos pos) {
        return SuperHopperT15BlockEntity.getInventoryAt(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
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

    public static void onEntityCollided(World world, BlockPos pos, BlockState state, Entity entity, SuperHopperT15BlockEntity blockEntity) {
        if (entity instanceof ItemEntity && VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entity.getBoundingBox().offset(-pos.getX(), -pos.getY(), -pos.getZ())), blockEntity.getInputAreaShape(), BooleanBiFunction.AND)) {
            SuperHopperT15BlockEntity.insertAndExtract(world, pos, state, blockEntity, () -> SuperHopperT15BlockEntity.extract(blockEntity, (ItemEntity)entity));
        }
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new SuperHopperScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}