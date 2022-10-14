package net.mooplemax.madmachines.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.mooplemax.madmachines.MadMachines;
import net.mooplemax.madmachines.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import javax.imageio.spi.RegisterableService;

public class ModBlockEntities {
    public static BlockEntityType<SuperFurnaceBlockEntity> SUPER_FURNACE;
    public static BlockEntityType<SuperHopperBlockEntity> SUPER_HOPPER;
    public static BlockEntityType<SuperHopperT2BlockEntity> SUPER_HOPPER_T2;
    public static BlockEntityType<SuperHopperT3BlockEntity> SUPER_HOPPER_T3;
    public static BlockEntityType<SuperHopperT4BlockEntity> SUPER_HOPPER_T4;
    public static BlockEntityType<SuperHopperT5BlockEntity> SUPER_HOPPER_T5;
    public static BlockEntityType<SuperHopperT6BlockEntity> SUPER_HOPPER_T6;
    public static BlockEntityType<SuperHopperT7BlockEntity> SUPER_HOPPER_T7;
    public static BlockEntityType<SuperHopperT8BlockEntity> SUPER_HOPPER_T8;
    public static BlockEntityType<SuperHopperT9BlockEntity> SUPER_HOPPER_T9;
    public static BlockEntityType<SuperHopperT10BlockEntity> SUPER_HOPPER_T10;
    public static BlockEntityType<SuperHopperT11BlockEntity> SUPER_HOPPER_T11;
    public static BlockEntityType<SuperHopperT12BlockEntity> SUPER_HOPPER_T12;
    public static BlockEntityType<SuperHopperT13BlockEntity> SUPER_HOPPER_T13;
    public static BlockEntityType<SuperHopperT14BlockEntity> SUPER_HOPPER_T14;
    public static BlockEntityType<SuperHopperT15BlockEntity> SUPER_HOPPER_T15;


    public static void registerBlockEntities() {

        SUPER_FURNACE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MadMachines.MOD_ID, "super_furnace"),
                FabricBlockEntityTypeBuilder.create(SuperFurnaceBlockEntity::new,
                    ModBlocks.SUPER_FURNACE).build(null));

        SUPER_HOPPER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper"),
                FabricBlockEntityTypeBuilder.create(SuperHopperBlockEntity::new,
                        ModBlocks.SUPER_HOPPER).build(null));
        SUPER_HOPPER_T2 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t2"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT2BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_2).build(null));
        SUPER_HOPPER_T3 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t3"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT3BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_3).build(null));
        SUPER_HOPPER_T4 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t4"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT4BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_4).build(null));
        SUPER_HOPPER_T5 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t5"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT5BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_5).build(null));
        SUPER_HOPPER_T6 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t6"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT6BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_6).build(null));
        SUPER_HOPPER_T7 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t7"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT7BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_7).build(null));
        SUPER_HOPPER_T8 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t8"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT8BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_8).build(null));
        SUPER_HOPPER_T9 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t9"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT9BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_9).build(null));
        SUPER_HOPPER_T10 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t10"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT10BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_10).build(null));
        SUPER_HOPPER_T11 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t11"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT11BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_11).build(null));
        SUPER_HOPPER_T12 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t12"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT12BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_12).build(null));
        SUPER_HOPPER_T13 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t13"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT13BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_13).build(null));
        SUPER_HOPPER_T14 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t14"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT14BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_14).build(null));
        SUPER_HOPPER_T15 = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper_t15"),
                FabricBlockEntityTypeBuilder.create(SuperHopperT15BlockEntity::new,
                        ModBlocks.SUPER_HOPPER_TIER_15).build(null));

    }
}
