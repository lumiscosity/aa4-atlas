package com.lumiscosity.aa4atlas;

import folk.sisby.antique_atlas.gui.AtlasScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class AA4AtlasClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(AtlasItem.GUI_PACKET_ID, (client, handler, buf, responseSender) -> {
			client.execute(() -> {
				if (client.currentScreen == null) {
					AtlasScreen screen = new AtlasScreen();
					screen.init();
					screen.prepareToOpen();
					screen.tick();
					client.setScreen(screen);
				}
			});
		});
	}
}