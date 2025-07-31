package com.lumiscosity.aa4atlas;

import folk.sisby.antique_atlas.gui.AtlasScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;

public class AA4AtlasClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		if (!FabricLoader.getInstance().isModLoaded("antique_atlas")) {
			throw new RuntimeException("Antique Atlas 4 is required for AA4 Atlas to function, but it wasn't found!");
		}
		ClientPlayNetworking.registerGlobalReceiver(AtlasOpenPayload.ID, (payload, context) -> {
			context.client().execute(() -> {
				if (context.client().currentScreen == null) {
					AtlasScreen screen = new AtlasScreen();
					screen.init();
					screen.prepareToOpen();
					screen.tick();
					context.client().setScreen(screen);
				}
			});
		});
	}
}