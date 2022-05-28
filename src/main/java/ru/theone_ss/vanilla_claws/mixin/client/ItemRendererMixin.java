package ru.theone_ss.vanilla_claws.mixin.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.theone_ss.vanilla_claws.client.TwoModelsItemRegistry;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModels models;
    @Shadow public abstract void renderItem(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model);

    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V",
            at = @At("HEAD"))
    void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci) {
        TwoModelsItemRegistry.ENTRIES.forEach((id, item) -> {
            if(!stack.isEmpty() && stack.isOf(item) && entity != null){
                BakedModel model = models.getModelManager().getModel(new ModelIdentifier(id + "_in_hand#inventory"));
                renderItem(stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, model);
                matrices.scale(0, 0, 0);
            }
        });
    }

}
