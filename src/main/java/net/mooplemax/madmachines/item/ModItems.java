package net.mooplemax.madmachines.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.mooplemax.madmachines.MadMachines;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item RAW_SILVER = registerItem("raw_silver",
            new Item(new FabricItemSettings().group(ModItemGroup.AUTOMATION)));
    public static final Item SILVER = registerItem("silver_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.AUTOMATION)));

    public static final Item RAW_COBALT = registerItem("raw_cobalt",
            new Item(new FabricItemSettings().group(ModItemGroup.AUTOMATION)));
    public static final Item COBALT = registerItem("cobalt_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.AUTOMATION)));

    public static final Item RAW_TITANIUM = registerItem("raw_titanium",
            new Item(new FabricItemSettings().group(ModItemGroup.AUTOMATION)));
    public static final Item TITANIUM = registerItem("titanium_ingot", new Item(new FabricItemSettings().group(ModItemGroup.AUTOMATION)));

    public static final Item RAW_PLATINUM = registerItem("raw_platinum",
            new Item(new FabricItemSettings().group(ModItemGroup.AUTOMATION)));
    public static final Item PLATINUM = registerItem("platinum_ingot", new Item(new FabricItemSettings().group(ModItemGroup.AUTOMATION)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MadMachines.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MadMachines.LOGGER.debug("Registering Mod Items for " + MadMachines.MOD_ID);
    }
}
