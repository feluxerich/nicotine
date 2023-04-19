package dev.fluxi;

import dev.fluxi.blocks.Blocks;
import dev.fluxi.items.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class NicotineClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TOBACCO_PLANT, RenderLayer.getCutout());

        ModelPredicateProviderRegistry.register(
                Items.CIGARETTE,
                new Identifier("remaining"),
                (itemStack, clientWorld, livingEntity, i) -> (itemStack.getMaxDamage() - itemStack.getDamage()) / 10F
        );
    }
}
