package net.mooplemax.madmachines.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.mooplemax.madmachines.MadMachines;
import net.mooplemax.madmachines.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final List<OreFeatureConfig.Target> OVERWORLD_SILVER_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SILVER_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> OVERWORLD_COBALT_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.COBALT_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_COBALT_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> OVERWORLD_TITANIUM_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TITANIUM_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> OVERWORLD_PLATINUM_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PLATINUM_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_PLATINUM_ORE.getDefaultState()));

    /*public static final List<OreFeatureConfig.Target> NETHER_TANZANITE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER, ModBlocks.NETHERRACK_XXXXX_ORE.getDefaultState()));

    public static final List<OreFeatureConfig.Target> END_TANZANITE_ORES = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE), ModBlocks.ENDSTONE_XXXXX_ORE.getDefaultState()));*/




    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> SILVER_ORE =
            ConfiguredFeatures.register("silver_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_SILVER_ORES, 4)); //size = maximum vein size
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> COBALT_ORE =
            ConfiguredFeatures.register("cobalt_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_COBALT_ORES, 9)); //size = maximum vein size
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> TITANIUM_ORE =
            ConfiguredFeatures.register("titanium_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_TITANIUM_ORES, 3)); //size = maximum vein size
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> PLATINUM_ORE =
            ConfiguredFeatures.register("platinum_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_PLATINUM_ORES, 2, 1.0F)); //size = maximum vein size

    /*public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> NETHER_XXXX_ORE =
            ConfiguredFeatures.register("nether_tanzanite_ore",Feature.ORE, new OreFeatureConfig(NETHER_XXXXX_ORES, 12));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> END_XXXXX_ORE =
            ConfiguredFeatures.register("end_tanzanite_ore",Feature.ORE, new OreFeatureConfig(END_XXXXX_ORES, 12));*/

    public static void registerConfiguredFeatures() {
        MadMachines.LOGGER.debug("Registering the ModConfiguredFeatures for " + MadMachines.MOD_ID);
    }
}
