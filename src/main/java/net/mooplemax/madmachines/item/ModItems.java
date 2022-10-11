package net.mooplemax.madmachines.item;

import net.mooplemax.madmachines.MadMachines;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    /*public static final Item TANZANITE = registerItem("tanzanite",
            new Item(new FabricItemSettings().group(ModItemGroup.TANZANITE)));*/


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MadMachines.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MadMachines.LOGGER.debug("Registering Mod Items for " + MadMachines.MOD_ID);
    }
}
