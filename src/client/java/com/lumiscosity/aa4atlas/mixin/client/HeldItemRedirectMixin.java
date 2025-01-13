package com.lumiscosity.aa4atlas.mixin.client;

import com.bawnorton.mixinsquared.TargetHandler;
import com.lumiscosity.aa4atlas.AtlasItem;
import folk.sisby.antique_atlas.mixin.MixinHeldItemRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.lumiscosity.aa4atlas.AA4Atlas.ANTIQUE_ATLAS;

@Mixin(value = HeldItemRenderer.class, priority = 1500)
public abstract class HeldItemRedirectMixin {
    @TargetHandler(
            mixin = "folk.sisby.antique_atlas.mixin.MixinHeldItemRenderer",
            name = "renderFirstPersonAtlas"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            )
    )
    private boolean checkForAtlasItem(ItemStack stack, Item item) {
        return stack.isOf(ANTIQUE_ATLAS);
    }

    @TargetHandler(
            mixin = "folk.sisby.antique_atlas.mixin.MixinHeldItemRenderer",
            name = "renderFirstPersonAtlas"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;contains(Ljava/lang/CharSequence;)Z"
            )
    )
    private boolean skipCheckForItemName(String string, CharSequence sequence) {
        return true;
    }
}
