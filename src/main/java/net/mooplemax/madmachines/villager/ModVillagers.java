package net.mooplemax.madmachines.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.mooplemax.madmachines.MadMachines;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(MadMachines.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static void registerVillagers() {
        MadMachines.LOGGER.debug("Registering Villagers for " + MadMachines.MOD_ID);
    }

    public static void registerTrades() {

    }
}
