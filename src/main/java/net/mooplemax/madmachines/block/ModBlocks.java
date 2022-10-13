package net.mooplemax.madmachines.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.util.math.intprovider.UniformIntProvider;
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
    public static final Block SILVER_ORE = registerBlock("silver_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool(), UniformIntProvider.create(3, 7)), ModItemGroup.AUTOMATION);
    public static final Block DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool(), UniformIntProvider.create(3, 7)), ModItemGroup.AUTOMATION);
    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool(), UniformIntProvider.create(3, 7)), ModItemGroup.AUTOMATION);
    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool(), UniformIntProvider.create(3, 7)), ModItemGroup.AUTOMATION);
    public static final Block TITANIUM_ORE = registerBlock("titanium_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool(), UniformIntProvider.create(3, 7)), ModItemGroup.AUTOMATION);
    public static final Block DEEPSLATE_TITANIUM_ORE = registerBlock("deepslate_titanium_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool(), UniformIntProvider.create(3, 7)), ModItemGroup.AUTOMATION);
    public static final Block PLATINUM_ORE = registerBlock("platinum_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool(), UniformIntProvider.create(30000, 70000)), ModItemGroup.AUTOMATION);
    public static final Block DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool(), UniformIntProvider.create(3, 7)), ModItemGroup.AUTOMATION);

    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool()), ModItemGroup.AUTOMATION);
    public static final Block SILVER_BLOCK = registerBlock("silver_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool()), ModItemGroup.AUTOMATION);
    public static final Block TITANIUM_BLOCK = registerBlock("titanium_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool()), ModItemGroup.AUTOMATION);
    public static final Block PLATINUM_BLOCK = registerBlock("platinum_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f, 4.8f).requiresTool()), ModItemGroup.AUTOMATION);


    public static final Block COMPRESSED_IRON_BLOCK = registerBlock("compressed_iron_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool().sounds(BlockSoundGroup.METAL)), ModItemGroup.AUTOMATION);
    public static final Block COMPRESSED_REDSTONE_BLOCK = registerBlock("compressed_redstone_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool().sounds(BlockSoundGroup.METAL)), ModItemGroup.AUTOMATION);
    public static final Block DOUBLE_COMPRESSED_REDSTONE_BLOCK = registerBlock("double_compressed_redstone_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool().sounds(BlockSoundGroup.METAL)), ModItemGroup.AUTOMATION);
    public static final Block TRIPLE_COMPRESSED_REDSTONE_BLOCK = registerBlock("triple_compressed_redstone_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool().sounds(BlockSoundGroup.METAL)), ModItemGroup.AUTOMATION);

    public static final Block SUPER_HOPPER = registerBlock("super_hopper",
            new SuperHopperBlock(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_2 = registerBlock("super_hopper_t2",
            new SuperHopperT2Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_3 = registerBlock("super_hopper_t3",
            new SuperHopperT3Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_4 = registerBlock("super_hopper_t4",
            new SuperHopperT4Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_5 = registerBlock("super_hopper_t5",
            new SuperHopperT5Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_6 = registerBlock("super_hopper_t6",
            new SuperHopperT6Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_7 = registerBlock("super_hopper_t7",
            new SuperHopperT7Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_8 = registerBlock("super_hopper_t8",
            new SuperHopperT8Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_9 = registerBlock("super_hopper_t9",
            new SuperHopperT9Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_10 = registerBlock("super_hopper_t10",
            new SuperHopperT10Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_11 = registerBlock("super_hopper_t11",
            new SuperHopperT11Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_12 = registerBlock("super_hopper_t12",
            new SuperHopperT12Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_13 = registerBlock("super_hopper_t13",
            new SuperHopperT13Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_14 = registerBlock("super_hopper_t14",
            new SuperHopperT14Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
                    .requiresTool().strength(3.0f, 4.8f).sounds(BlockSoundGroup.METAL).nonOpaque()), ModItemGroup.AUTOMATION);
    public static final Block SUPER_HOPPER_TIER_15 = registerBlock("super_hopper_t15",
            new SuperHopperT15Block(FabricBlockSettings.of(Material.METAL, MapColor.STONE_GRAY)
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
