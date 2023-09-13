package ru.theone_ss.vanilla_claws.registry;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ru.theone_ss.vanilla_claws.VanillaClaws;
import ru.theone_ss.vanilla_claws.compat.WinterlyIntegration;
import ru.theone_ss.vanilla_claws.item.ClawsItem;
import ru.theone_ss.vanilla_claws.item.CryomarbleClawsItem;
import ru.theone_ss.vanilla_claws.item.material.VanillaClawsMaterials;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class VanillaClawsItems {
    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    public static final Item NETHERITE_CLAWS = add("netherite_claws", new ClawsItem(VanillaClawsMaterials.NETHERITE, 5, 2, new Item.Settings()));
    public static final Item DIAMOND_CLAWS = add("diamond_claws", new ClawsItem(VanillaClawsMaterials.DIAMOND, 4, 2, new Item.Settings()));
    public static final Item IRON_CLAWS = add("iron_claws", new ClawsItem(VanillaClawsMaterials.IRON, 5, 2, new Item.Settings()));
    public static final Item GOLDEN_CLAWS = add("golden_claws", new ClawsItem(VanillaClawsMaterials.GOLD, 4, 2, new Item.Settings()));
    public static final Item COPPER_CLAWS = add("copper_claws", new ClawsItem(VanillaClawsMaterials.COPPER, 4, 2, new Item.Settings()));
    public static final Item STONE_CLAWS = add("stone_claws", new ClawsItem(VanillaClawsMaterials.STONE, 4, 2, new Item.Settings()));
    public static final Item WOODEN_CLAWS = add("wooden_claws", new ClawsItem(VanillaClawsMaterials.WOOD, 3, 2, new Item.Settings()));

    public static final Item CRYOMARBLE_CLAWS = add("cryomarble_claws", new CryomarbleClawsItem(material("winterly"), 4, 2, new Item.Settings()));

    private static ToolMaterial material(String modId) {
        if(FabricLoader.getInstance().isModLoaded(modId)) {
            if(modId.equals("winterly")) {
                return WinterlyIntegration.getMaterial();
            }
        }
        return VanillaClawsMaterials.IRON;
    }

    private static Item add(String name, Item item) {
        ITEMS.put(VanillaClaws.id(name), item);
        return item;
    }

    public static void init() {
        ITEMS.forEach((id, item) -> Registry.register(Registries.ITEM,id, item));
    }

}
