package net.kaupenjoe.tutorialmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup AUTOMATION = FabricItemGroupBuilder.build(
            new Identifier(TutorialMod.MOD_ID, "automation"), () -> new ItemStack(ModBlocks.SUPER_HOPPER));
}
