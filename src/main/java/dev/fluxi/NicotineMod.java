package dev.fluxi;

import dev.fluxi.blocks.Blocks;
import dev.fluxi.items.Items;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NicotineMod implements ModInitializer {
	public static final String MOD_ID = "nicotine";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Items.registerItems();
		Blocks.registerBlocks();
	}
}