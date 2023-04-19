package dev.fluxi.blocks;

import dev.fluxi.NicotineMod;
import dev.fluxi.blocks.custom.TobaccoPlantBlock;
import dev.fluxi.items.Items;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Blocks {
    public static Block TOBACCO_PLANT = null;

    public static void registerBlocks() {
        TOBACCO_PLANT = register("tobacco_plant", new TobaccoPlantBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()));
    }

    private static Block register(String identifier, Block block) {
        return register(identifier, block, null);
    }

    private static Block register(String identifier, Block block, FabricItemSettings settings) {
        if (settings != null) {
            Items.register(identifier, new BlockItem(block, settings));
        }
        return Registry.register(Registries.BLOCK, new Identifier(NicotineMod.MOD_ID, identifier), block);
    }
}
