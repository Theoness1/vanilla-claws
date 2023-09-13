package ru.theone_ss.vanilla_claws.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MatrixUtil;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.theone_ss.vanilla_claws.client.TwoModelsItemRegistry;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModels models;

    @Shadow @Final private BuiltinModelItemRenderer builtinModelItemRenderer;

    @Shadow protected abstract void renderBakedItemModel(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices, VertexConsumer vertices);

    @Shadow
    private static boolean usesDynamicDisplay(ItemStack stack) {
        return false;
    }

    @Shadow
    public static VertexConsumer getDirectDynamicDisplayGlintConsumer(VertexConsumerProvider provider, RenderLayer layer, MatrixStack.Entry entry) {
        return null;
    }

    @Shadow
    public static VertexConsumer getDynamicDisplayGlintConsumer(VertexConsumerProvider provider, RenderLayer layer, MatrixStack.Entry entry) {
        return null;
    }

    @Shadow
    public static VertexConsumer getDirectItemGlintConsumer(VertexConsumerProvider provider, RenderLayer layer, boolean solid, boolean glint) {
        return null;
    }

    @Shadow
    public static VertexConsumer getItemGlintConsumer(VertexConsumerProvider vertexConsumers, RenderLayer layer, boolean solid, boolean glint) {
        return null;
    }

    @Inject(method = "getModel(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)Lnet/minecraft/client/render/model/BakedModel;",
            at = @At("HEAD"), cancellable = true)
    void getModel(ItemStack stack, World world, LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        TwoModelsItemRegistry.ENTRIES.forEach((id, item) -> {
            if (!stack.isEmpty() && stack.isOf(item) && entity != null) {
                BakedModel model = models.getModelManager().getModel(new ModelIdentifier(new Identifier(id + "_in_hand"), "inventory"));
                cir.setReturnValue(model);
            }
        });
    }

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At("HEAD"), cancellable = true )
    void renderItem(ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if (!stack.isEmpty()) {
            TwoModelsItemRegistry.ENTRIES.forEach((identifier, item) -> {
                if (stack.isOf(item)) {
                    boolean bl = renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED;
                    if (bl) {
                        matrices.push();
                        BakedModel bakedModel = models.getModelManager().getModel(new ModelIdentifier(identifier, "inventory"));
                        bakedModel.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
                        matrices.translate(-0.5F, -0.5F, -0.5F);
                        if (!bakedModel.isBuiltin()) {
                            boolean bl2;
                            if (renderMode != ModelTransformationMode.GUI && !renderMode.isFirstPerson() && stack.getItem() instanceof BlockItem) {
                                Block block = ((BlockItem)stack.getItem()).getBlock();
                                bl2 = !(block instanceof TransparentBlock) && !(block instanceof StainedGlassPaneBlock);
                            } else {
                                bl2 = true;
                            }

                            RenderLayer renderLayer = RenderLayers.getItemLayer(stack, bl2);
                            VertexConsumer vertexConsumer;
                            if (usesDynamicDisplay(stack) && stack.hasGlint()) {
                                matrices.push();
                                MatrixStack.Entry entry = matrices.peek();
                                if (renderMode == ModelTransformationMode.GUI) {
                                    MatrixUtil.scale(entry.getPositionMatrix(), 0.5F);
                                } else if (renderMode.isFirstPerson()) {
                                    MatrixUtil.scale(entry.getPositionMatrix(), 0.75F);
                                }

                                if (bl2) {
                                    vertexConsumer = getDirectDynamicDisplayGlintConsumer(vertexConsumers, renderLayer, entry);
                                } else {
                                    vertexConsumer = getDynamicDisplayGlintConsumer(vertexConsumers, renderLayer, entry);
                                }

                                matrices.pop();
                            } else if (bl2) {
                                vertexConsumer = getDirectItemGlintConsumer(vertexConsumers, renderLayer, true, stack.hasGlint());
                            } else {
                                vertexConsumer = getItemGlintConsumer(vertexConsumers, renderLayer, true, stack.hasGlint());
                            }

                            this.renderBakedItemModel(bakedModel, stack, light, overlay, matrices, vertexConsumer);
                        } else {
                            this.builtinModelItemRenderer.render(stack, renderMode, matrices, vertexConsumers, light, overlay);
                        }

                        matrices.pop();
                        ci.cancel();
                    }
                }
            });
        }
    }
}
