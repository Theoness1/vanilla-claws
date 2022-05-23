package ru.theone_ss.vanilla_claws.item.material;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum VanillaClawsMaterial implements ToolMaterial {

    WOOD(0, 79, 2.0F, 0.0F, 1, () -> Ingredient.fromTag(ItemTags.PLANKS)),
    STONE(0, 161, 4.0F, 0.0F, 1, () -> Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS)),
    COPPER(0, 231, 2.0F, 0.0F, 1, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    IRON(0, 320, 6.0F, 0.0F, 1, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    DIAMOND(0, 761, 8.0F, 2.0F, 1, () -> Ingredient.ofItems(Items.DIAMOND)),
    GOLD(0, 62, 12.0F, 0.0F, 26, () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    NETHERITE(0, 1131, 9.0F, 2.0F, 1, () -> Ingredient.ofItems(Items.NETHERITE_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    VanillaClawsMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
