package com.lumiscosity.aa4atlas;

import folk.sisby.antique_atlas.gui.AtlasScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lumiscosity.aa4atlas.AA4Atlas.MOD_ID;

public class AA4AtlasClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		if (!FabricLoader.getInstance().isModLoaded("antique_atlas")) {
			LOGGER.error("Antique Atlas 4 is not installed! AA4 Atlas will not do anything.");
		}
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