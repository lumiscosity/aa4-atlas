package com.lumiscosity.aa4atlas;

import folk.sisby.antique_atlas.gui.AtlasScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AA4AtlasClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
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