package net.mooplemax.madmachines;

import net.fabricmc.api.ClientModInitializer;
import net.mooplemax.madmachines.screen.ModScreenHandlers;
import net.mooplemax.madmachines.screen.SuperHopperScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;


public class MadMachinesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.SUPER_HOPPER_SCREEN_HANDLER, SuperHopperScreen::new);

    }
}
