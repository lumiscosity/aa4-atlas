package com.lumiscosity.aa4atlas;

import folk.sisby.surveyor.Surveyor;
import folk.sisby.surveyor.config.SurveyorConfig;
import folk.sisby.surveyor.config.SystemMode;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AA4Atlas implements ModInitializer {
	public static final String MOD_ID = "aa4-atlas";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final SurveyorConfig BACKUP_SURVEYOR_CONFIG = (SurveyorConfig)SurveyorConfig.createToml(FabricLoader.getInstance().getConfigDir(), "", "aa4-atlas-svbackup", SurveyorConfig.class);
	public static boolean RELOAD = false;

	public static final Item ANTIQUE_ATLAS = new AtlasItem(new Item.Settings().maxCount(1));

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "antique_atlas"), ANTIQUE_ATLAS);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.addAfter(Items.WRITABLE_BOOK, ANTIQUE_ATLAS);
		});
	}
}