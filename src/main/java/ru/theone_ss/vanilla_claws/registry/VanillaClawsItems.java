package ru.theone_ss.vanilla_claws.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.theone_ss.vanilla_claws.item.ClawsItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class VanillaClawsItems {

    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    public static final Item NETHERITE_CLAWS = add("netherite_claws", new ClawsItem(ToolMaterials.NETHERITE, 3, 3, settings()));
    public static final Item DIAMOND_CLAWS = add("diamond_claws", new ClawsItem(ToolMaterials.DIAMOND, 3, 3, settings()));
    public static final Item IRON_CLAWS = add("iron_claws", new ClawsItem(ToolMaterials.IRON, 3, 3, settings()));
    public static final Item GOLDEN_CLAWS = add("golden_claws", new ClawsItem(ToolMaterials.GOLD, 3, 3, settings()));
    public static final Item COPPER_CLAWS = add("copper_claws", new ClawsItem(ToolMaterials.STONE, 3, 3, settings()));
    public static final Item STONE_CLAWS = add("stone_claws", new ClawsItem(ToolMaterials.STONE, 3, 3, settings()));
    public static final Item WOODEN_CLAWS = add("wooden_claws", new ClawsItem(ToolMaterials.WOOD, 3, 3, settings()));

    private static Item add(String name, Item item) {
        ITEMS.put(new Identifier("vanilla_claws", name), item);
        return item;
    }

    private static FabricItemSettings settings() {
        FabricItemSettings settings = new FabricItemSettings();
        settings.group(ItemGroup.COMBAT);
        return settings;
    }

    public static void init() {
        ITEMS.forEach((id, item) -> Registry.register(Registry.ITEM, id, item));
    }


}
