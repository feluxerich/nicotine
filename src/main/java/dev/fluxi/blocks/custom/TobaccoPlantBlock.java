package dev.fluxi.blocks.custom;

import dev.fluxi.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;


public class TobaccoPlantBlock extends CropBlock {
    public static final int MAX_AGE = 9;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(7, 0, 7, 9, 2, 9),
            Block.createCuboidShape(7, 0, 7, 9, 4, 9),
            Block.createCuboidShape(7, 0, 7, 9, 6, 9),
            Block.createCuboidShape(7, 0, 7, 9, 8, 9),
            Block.createCuboidShape(0, 0, 0, 16, 8, 16),
    };

    public TobaccoPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(AGE) > AGE_TO_SHAPE.length - 1) {
            return AGE_TO_SHAPE[AGE_TO_SHAPE.length - 1];
        }
        return AGE_TO_SHAPE[state.get(AGE)];
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.TOBACCO_SEEDS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        int age = state.get(AGE);
        if (!itemStack.isOf(Items.SHEARS) || age <= 3) {
            return ActionResult.FAIL;
        }
        if (!player.isCreative()) {
            itemStack.damage(1, player, (ServerPlayerEntity) -> {});
        }
        ItemStack dropItemStack = new ItemStack(getHarvestItem());
        dropItemStack.setCount(age - 3);
        world.setBlockState(pos, state.with(AGE, 3));
        dropStack(world, pos, dropItemStack);
        return ActionResult.SUCCESS;
    }

    public Item getHarvestItem() {
        return ModItems.CUT_TOBACCO; // TODO: Make this to a normal tobacco leaf
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT);
    }
}