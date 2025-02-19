package com.lumiscosity.aa4atlas.mixin.client;

import folk.sisby.antique_atlas.AntiqueAtlas;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;

import static com.lumiscosity.aa4atlas.AA4Atlas.ANTIQUE_ATLAS;

@Mixin(AntiqueAtlas.class)
public class HandheldItemMixin {
    /**
     * @author lumiscosity
     * @reason switch the handheld atlas to the aa4 atlas item
     */
    @Overwrite
    public static ItemStack getHandheldAtlas() {
        return ANTIQUE_ATLAS.getDefaultStack();
    }

    /**
     * @author lumiscosity
     * @reason switch the handheld atlas to the aa4 atlas item
     */
    @Overwrite
    public static boolean isHandheldAtlas(ItemStack stack) {
        return stack.isOf(ANTIQUE_ATLAS);
    }
}
