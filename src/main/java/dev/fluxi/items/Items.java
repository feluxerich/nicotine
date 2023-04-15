package dev.fluxi.items;

import dev.fluxi.NicotineMod;
import dev.fluxi.items.custom.CigaretteItem;
import dev.fluxi.items.custom.FilterTipPaperItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class Items {
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier("nicotine", "main"))
            .icon(() -> new ItemStack(Items.CIGARETTE)).build();

    public static Item CIGARETTE = null;
    public static Item ROLLING_PAPER = null;
    public static Item ACTIVATED_CHARCOAL_FILTER = null;
    public static Item FILTER_TIP_PAPER = null;
    public static Item FILTER_TIP = null;
    public static Item FILTER = null;
    public static Item CUT_TOBACCO = null;

    public static void registerAll() {
        CIGARETTE = register("cigarette", new CigaretteItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxDamage(10)));
        ROLLING_PAPER = register("rolling_paper", new Item(new FabricItemSettings()));
        ACTIVATED_CHARCOAL_FILTER = register("activated_charcoal_filter", new Item(new FabricItemSettings()));
        FILTER_TIP_PAPER = register("filter_tip_paper", new FilterTipPaperItem(new FabricItemSettings().maxCount(1)));
        FILTER_TIP = register("filter_tip", new Item(new FabricItemSettings()));
        FILTER = register("filter", new Item(new FabricItemSettings()));
        CUT_TOBACCO = register("cut_tobacco", new Item(new FabricItemSettings()));
    }

    private static Item register(String identifier, Item item) {
        ItemGroupEvents.modifyEntriesEvent(Items.ITEM_GROUP).register(content -> content.add(item));
        return Registry.register(Registries.ITEM, new Identifier(NicotineMod.MOD_ID, identifier), item);
    }
}
