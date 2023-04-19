package dev.fluxi.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;


public class TobaccoPlantBlock extends PlantBlock implements Fertilizable {
    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(7, 0, 7, 9, 2, 9),
            Block.createCuboidShape(7, 0, 7, 9, 4, 9),
            Block.createCuboidShape(7, 0, 7, 9, 6, 9),
            Block.createCuboidShape(7, 0, 7, 9, 8, 9),
            Block.createCuboidShape(0, 0, 0, 16, 8, 16),
    };
    public static final int MAX_AGE = 9;
    public static final IntProperty AGE = IntProperty.of("stage", 0, MAX_AGE);


    public TobaccoPlantBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(AGE, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(AGE) > SHAPES.length - 1) {
            return SHAPES[SHAPES.length - 1];
        }
        return SHAPES[state.get(AGE)];
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
    }
}