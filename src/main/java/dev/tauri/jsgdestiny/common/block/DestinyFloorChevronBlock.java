package dev.tauri.jsgdestiny.common.block;

import dev.tauri.jsg.api.item.JSGBlockItem;
import dev.tauri.jsgdestiny.common.blockentity.DestinyFloorChevronBE;
import dev.tauri.jsgdestiny.common.item.DestinyFloorChevronItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class DestinyFloorChevronBlock extends DestinyBearingBlock {
    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Shapes.create(0, 0, 0, 1, 1f / 16f, 1);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DestinyFloorChevronBE(pPos, pState);
    }

    @Override
    public JSGBlockItem getItemBlock() {
        return new DestinyFloorChevronItem(this);
    }
}
