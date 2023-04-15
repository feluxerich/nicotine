package dev.fluxi;

import dev.fluxi.items.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class NicotineClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(
                Items.CIGARETTE,
                new Identifier("remaining"),
                (itemStack, clientWorld, livingEntity, i) -> (itemStack.getMaxDamage() - itemStack.getDamage()) / 10F
        );
    }
}
