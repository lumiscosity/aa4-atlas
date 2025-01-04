package com.lumiscosity.aa4atlas.mixin.client;

import com.lumiscosity.aa4atlas.AtlasItem;
import net.minecraft.client.MinecraftClient;
import folk.sisby.antique_atlas.AntiqueAtlasKeybindings;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.lumiscosity.aa4atlas.AA4Atlas.ANTIQUE_ATLAS;
import static com.lumiscosity.aa4atlas.AA4Atlas.LOGGER;
import static folk.sisby.antique_atlas.AntiqueAtlasKeybindings.ATLAS_KEYMAPPING;

@Mixin(AntiqueAtlasKeybindings.class)
public class KeybindItemCheckMixin {
	@Shadow @Final public static KeyBinding ATLAS_KEYMAPPING;

	@Inject(method = "Lfolk/sisby/antique_atlas/AntiqueAtlasKeybindings;onClientTick(Lnet/minecraft/client/MinecraftClient;)V", at = @At("HEAD"), cancellable = true)
	private static void onClientTick(MinecraftClient client, CallbackInfo ci) {
        if (client.player != null && !client.player.getInventory().contains(ANTIQUE_ATLAS.getDefaultStack())) {
			ATLAS_KEYMAPPING.wasPressed();
			ci.cancel();
		}
	}
}
