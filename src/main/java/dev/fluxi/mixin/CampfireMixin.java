package dev.fluxi.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class CampfireMixin {
    private static final BooleanProperty HAS_GRATING = BooleanProperty.of("has_grating"); // TODO: set default value to false
    private static final VoxelShape SHAPE_WITH_GRATING = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    @Inject(method = "appendProperties", at = @At("HEAD"))
    private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(HAS_GRATING);
    }

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    private void getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.get(HAS_GRATING)) {
            cir.setReturnValue(SHAPE_WITH_GRATING);
        }
    }
}
