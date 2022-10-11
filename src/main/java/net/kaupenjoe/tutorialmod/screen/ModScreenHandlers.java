package net.kaupenjoe.tutorialmod.screen;

import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<SuperHopperScreenHandler> SUPER_HOPPER_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        SUPER_HOPPER_SCREEN_HANDLER = new ScreenHandlerType<>(SuperHopperScreenHandler::new);
    }
}
