package dev.fluxi;

import dev.fluxi.blocks.ModBlocks;
import dev.fluxi.client.NicotineStatusOverlay;
import dev.fluxi.items.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class NicotineClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOBACCO_PLANT, RenderLayer.getCutout());

        HudRenderCallback.EVENT.register(new NicotineStatusOverlay());

        ModelPredicateProviderRegistry.register(
                ModItems.CIGARETTE,
                new Identifier("remaining"),
                (itemStack, clientWorld, livingEntity, i) -> (itemStack.getMaxDamage() - itemStack.getDamage()) / 10F
        );
    }
}
