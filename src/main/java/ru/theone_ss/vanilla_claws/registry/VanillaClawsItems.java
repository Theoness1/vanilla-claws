package ru.theone_ss.vanilla_claws.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.theone_ss.vanilla_claws.compat.WinterlyIntegration;
import ru.theone_ss.vanilla_claws.item.ClawsItem;
import ru.theone_ss.vanilla_claws.item.material.VanillaClawsMaterial;

import java.util.LinkedHashMap;
import java.util.Map;

public class VanillaClawsItems {

    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    public static final Item NETHERITE_CLAWS = add("netherite_claws", new ClawsItem(VanillaClawsMaterial.NETHERITE, 4, 2, settings()));
    public static final Item DIAMOND_CLAWS = add("diamond_claws", new ClawsItem(VanillaClawsMaterial.DIAMOND, 3, 2, settings()));
    public static final Item IRON_CLAWS = add("iron_claws", new ClawsItem(VanillaClawsMaterial.IRON, 4, 2, settings()));
    public static final Item GOLDEN_CLAWS = add("golden_claws", new ClawsItem(VanillaClawsMaterial.GOLD, 3, 2, settings()));
    public static final Item COPPER_CLAWS = add("copper_claws", new ClawsItem(VanillaClawsMaterial.COPPER, 2, 2, settings()));
    public static final Item STONE_CLAWS = add("stone_claws", new ClawsItem(VanillaClawsMaterial.STONE, 3, 2, settings()));
    public static final Item WOODEN_CLAWS = add("wooden_claws", new ClawsItem(VanillaClawsMaterial.WOOD, 2, 2, settings()));

    public static final Item CRYOMARBLE_CLAWS = add("cryomarble_claws", new ClawsItem(getMaterial("winterly"), 3, 2, settings("winterly")));

    private static ToolMaterial getMaterial(String modId){

        if(FabricLoader.getInstance().isModLoaded(modId)) {
            if(modId.equals("winterly")){
                return WinterlyIntegration.getMaterial();
            }
        }
        return VanillaClawsMaterial.IRON;
    }

    private static Item add(String name, Item item) {
        ITEMS.put(new Identifier("vanilla_claws", name), item);
        return item;
    }

    private static FabricItemSettings settings() {
        FabricItemSettings settings = new FabricItemSettings();
        settings.group(ItemGroup.COMBAT);
        return settings;
    }

    private static FabricItemSettings settings(String modId) {
        FabricItemSettings settings = new FabricItemSettings();
        if(FabricLoader.getInstance().isModLoaded(modId)) {
            settings.group(ItemGroup.COMBAT);
        }
        return settings;
    }

    public static void init() {
        ITEMS.forEach((id, item) -> Registry.register(Registry.ITEM, id, item));
    }


}
