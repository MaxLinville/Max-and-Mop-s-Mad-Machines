package net.mooplemax.madmachines.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.mooplemax.madmachines.MadMachines;
import net.mooplemax.madmachines.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup AUTOMATION = FabricItemGroupBuilder.build(
            new Identifier(MadMachines.MOD_ID, "automation"), () -> new ItemStack(ModBlocks.SUPER_HOPPER));
}
