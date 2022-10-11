package net.mooplemax.madmachines.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mooplemax.madmachines.MadMachines;
import net.mooplemax.madmachines.block.custom.*;
import net.mooplemax.madmachines.item.ModItemGroup;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    /*public static final Block TANZANITE_BLOCK = registerBlock("tanzanite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.TANZANITE);*/

    public static final Block SUPER_HOPPER = registerBlock("super_hopper",
            new SuperHopperBlock(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MadMachines.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(MadMachines.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(MadMachines.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        MadMachines.LOGGER.debug("Registering ModBlocks for " + MadMachines.MOD_ID);
    }
}
