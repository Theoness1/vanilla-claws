package ru.theone_ss.vanilla_claws.compat;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import ru.theone_ss.vanilla_claws.item.material.BaseToolMaterial;

public class WinterlyIntegration {
    public static ToolMaterial CRYOMARBLE_MATERIAL = new BaseToolMaterial(3, 521, 8.0F, 2.0F, 1, () -> Ingredient.ofItems(Registries.ITEM.get(new Identifier("winterly", "cryomarble"))));

    public static ToolMaterial getMaterial() {
        return CRYOMARBLE_MATERIAL;
    }

    public static void init() {

    }

}
