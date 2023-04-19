package dev.fluxi;

import dev.fluxi.blocks.ModBlocks;
import dev.fluxi.items.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NicotineMod implements ModInitializer {
	public static final String MOD_ID = "nicotine";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModBlocks.registerBlocks();
	}
}