package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.kaupenjoe.tutorialmod.screen.ModScreenHandlers;
import net.kaupenjoe.tutorialmod.screen.SuperHopperScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;


public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.SUPER_HOPPER_SCREEN_HANDLER, SuperHopperScreen::new);

    }
}
