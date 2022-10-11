package net.mooplemax.madmachines.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.mooplemax.madmachines.MadMachines;
import net.mooplemax.madmachines.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<SuperHopperBlockEntity> SUPER_HOPPER;

    public static void registerBlockEntities() {

        SUPER_HOPPER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MadMachines.MOD_ID, "super_hopper"),
                FabricBlockEntityTypeBuilder.create(SuperHopperBlockEntity::new,
                        ModBlocks.SUPER_HOPPER).build(null));
    }
}
