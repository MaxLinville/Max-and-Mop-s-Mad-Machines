package net.kaupenjoe.tutorialmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<SuperHopperBlockEntity> SUPER_HOPPER;

    public static void registerBlockEntities() {

        SUPER_HOPPER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(TutorialMod.MOD_ID, "super_hopper"),
                FabricBlockEntityTypeBuilder.create(SuperHopperBlockEntity::new,
                        ModBlocks.SUPER_HOPPER).build(null));
    }
}
