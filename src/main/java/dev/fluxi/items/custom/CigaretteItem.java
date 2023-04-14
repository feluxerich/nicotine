package dev.fluxi.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CigaretteItem extends Item {
    public CigaretteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() == 0 && !user.getOffHandStack().getItem().equals(Items.FLINT_AND_STEEL)) {
            return TypedActionResult.fail(itemStack);
        }
        itemStack.damage(1, user, playerEntity -> {});
        return TypedActionResult.success(itemStack);
    }
}
