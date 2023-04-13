package dev.fluxi.items;

import dev.fluxi.NicotineMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class Items {
    public static Item CIGARETTE = null;
    public static void registerAll() {
        CIGARETTE = register("cigarette", new CigaretteItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxDamage(10)));
    }

    private static Item register(String identifier, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(NicotineMod.MOD_ID, identifier), item);
    }
}
