package net.mooplemax.madmachines.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> SILVER_ORE_PLACED = PlacedFeatures.register("silver_ore_placed",
            ModConfiguredFeatures.SILVER_ORE, modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(32))));
    public static final RegistryEntry<PlacedFeature> COBALT_ORE_PLACED = PlacedFeatures.register("cobalt_ore_placed",
            ModConfiguredFeatures.COBALT_ORE, modifiersWithCount(10, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56))));
    public static final RegistryEntry<PlacedFeature> TITANIUM_ORE_PLACED = PlacedFeatures.register("titanium_ore_placed",
            ModConfiguredFeatures.TITANIUM_ORE, modifiersWithCount(3, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
    public static final RegistryEntry<PlacedFeature> PLATINUM_ORE_PLACED = PlacedFeatures.register("platinum_ore_placed",
            ModConfiguredFeatures.PLATINUM_ORE, modifiersWithCount(2, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));

    /*ORE_IRON_UPPER = PlacedFeatures.register("ore_iron_upper", OreConfiguredFeatures.ORE_IRON, modifiersWithCount(90, HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384))));
    ORE_IRON_MIDDLE = PlacedFeatures.register("ore_iron_middle", OreConfiguredFeatures.ORE_IRON, modifiersWithCount(10, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56))));
    ORE_IRON_SMALL = PlacedFeatures.register("ore_iron_small", OreConfiguredFeatures.ORE_IRON_SMALL, modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72))));

    ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large", OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE, new PlacementModifier[]{SquarePlacementModifier.of(), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()});
    ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small", OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL, new PlacementModifier[]{SquarePlacementModifier.of(), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()});

    ORE_DIAMOND = PlacedFeatures.register("ore_diamond", OreConfiguredFeatures.ORE_DIAMOND_SMALL, modifiersWithCount(7, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
    ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large", OreConfiguredFeatures.ORE_DIAMOND_LARGE, modifiersWithRarity(9, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
    ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried", OreConfiguredFeatures.ORE_DIAMOND_BURIED, modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));

    ORE_GOLD = PlacedFeatures.register("ore_gold", OreConfiguredFeatures.ORE_GOLD_BURIED, modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(32))));
     */


    /*public static final RegistryEntry<PlacedFeature> NETHER_XXXXX_ORE_PLACED = PlacedFeatures.register("nether_XXXXX_ore_placed",
            ModConfiguredFeatures.NETHER_XXXXX_ORE, modifiersWithCount(10,
                    HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));

    public static final RegistryEntry<PlacedFeature> END_XXXXXX_ORE_PLACED = PlacedFeatures.register("end_XXXXX_ore_placed",
            ModConfiguredFeatures.END_XXXXX_ORE, modifiersWithCount(10,
                    HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));*/



    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }
    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }
    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
