package net.mooplemax.madmachines;

import net.fabricmc.api.ModInitializer;
import net.mooplemax.madmachines.block.ModBlocks;
import net.mooplemax.madmachines.block.entity.ModBlockEntities;
import net.mooplemax.madmachines.fluid.ModFluids;
import net.mooplemax.madmachines.item.ModItems;
import net.mooplemax.madmachines.screen.ModScreenHandlers;
import net.mooplemax.madmachines.util.ModLootTableModifiers;
import net.mooplemax.madmachines.villager.ModVillagers;
import net.mooplemax.madmachines.world.feature.ModConfiguredFeatures;
import net.mooplemax.madmachines.world.gen.ModOreGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MadMachines implements ModInitializer {
	public static final String MOD_ID = "madmachines";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfiguredFeatures.registerConfiguredFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModVillagers.registerVillagers();
		ModVillagers.registerTrades();

		ModOreGeneration.generateOres();

		ModLootTableModifiers.modifyLootTables();

		ModFluids.register();
		ModBlockEntities.registerBlockEntities();

		ModScreenHandlers.registerAllScreenHandlers();
	}
}
