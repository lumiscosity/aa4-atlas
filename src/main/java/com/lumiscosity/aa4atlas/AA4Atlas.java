package com.lumiscosity.aa4atlas;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AA4Atlas implements ModInitializer {
	public static final String MOD_ID = "aa4-atlas";

	public static final Item ANTIQUE_ATLAS = new AtlasItem(new Item.Settings().maxCount(1));

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "antique_atlas"), ANTIQUE_ATLAS);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.addAfter(Items.WRITABLE_BOOK, ANTIQUE_ATLAS);
		});
		PayloadTypeRegistry.playS2C().register(AtlasOpenPayload.ID, AtlasOpenPayload.CODEC);
	}
}