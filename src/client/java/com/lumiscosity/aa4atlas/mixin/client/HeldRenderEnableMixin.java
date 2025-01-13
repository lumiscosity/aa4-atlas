package com.lumiscosity.aa4atlas.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static com.lumiscosity.aa4atlas.AA4Atlas.ANTIQUE_ATLAS;

@Mixin(value = HeldItemRenderer.class, priority = 900)
public class HeldRenderEnableMixin {
    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean enableFirstPersonAtlasRendering(boolean original, AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack stack, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (stack.isOf(Items.BOOK) && stack.getName().getString().contains("Antique Atlas")) {
            return false;
        } else {
            return original || stack.isOf(ANTIQUE_ATLAS);
        }
    }
}
