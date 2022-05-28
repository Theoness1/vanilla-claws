package ru.theone_ss.vanilla_claws.compat;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import ru.theone_ss.vanilla_claws.item.material.BaseToolMaterial;
import ru.tlmclub.winterly.registry.WinterlyItems;

public class WinterlyIntegration {
    public static ToolMaterial CRYOMARBLE_MATERIAL = new BaseToolMaterial(3, 521, 8.0F, 2.0F, 1, () -> Ingredient.ofItems(WinterlyItems.CRYOMARBLE));

    public static ToolMaterial getMaterial() {
        return CRYOMARBLE_MATERIAL;
    }

    public static void init() {

    }

}
