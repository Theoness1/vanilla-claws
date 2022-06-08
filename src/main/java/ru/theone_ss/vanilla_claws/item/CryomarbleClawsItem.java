package ru.theone_ss.vanilla_claws.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class CryomarbleClawsItem extends IntegratedClawsItem {

    public CryomarbleClawsItem(ToolMaterial material, int damage, float attackSpeed, Settings settings) {
        super("winterly", material, damage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0));
        return super.postHit(stack, target, attacker);
    }
}
