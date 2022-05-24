package ru.theone_ss.vanilla_claws.mixin.common;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.theone_ss.vanilla_claws.item.ClawsItem;

@Mixin(ShieldItem.class)
public abstract class ShieldMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        if(user.getStackInHand(Hand.MAIN_HAND).getItem()instanceof ClawsItem){
            cir.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)));
        }
    }

}
