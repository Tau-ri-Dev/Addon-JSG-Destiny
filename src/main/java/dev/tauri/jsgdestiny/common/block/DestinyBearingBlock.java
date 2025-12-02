package dev.tauri.jsgdestiny.common.block;

import dev.tauri.jsg.block.IHighlightBlock;
import dev.tauri.jsg.block.IItemBlock;
import dev.tauri.jsg.block.TickableBEBlock;
import dev.tauri.jsg.block.WrenchRotatable;
import dev.tauri.jsg.helpers.BlockPosHelper;
import dev.tauri.jsg.helpers.ItemHelper;
import dev.tauri.jsg.item.ITabbedItem;
import dev.tauri.jsg.item.JSGBlockItem;
import dev.tauri.jsg.property.JSGProperties;
import dev.tauri.jsg.stargate.Stargate;
import dev.tauri.jsgdestiny.common.blockentity.DestinyBearingBE;
import dev.tauri.jsgdestiny.common.item.DestinyBearingItem;
import dev.tauri.jsgdestiny.common.registry.TabRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class DestinyBearingBlock extends TickableBEBlock implements IHighlightBlock, SimpleWaterloggedBlock, ITabbedItem, WrenchRotatable, IItemBlock {
    public DestinyBearingBlock() {
        super(Properties.copy(Blocks.IRON_BLOCK).isViewBlocking((BlockState state, BlockGetter getter, BlockPos pos) -> false).noOcclusion());
        this.registerDefaultState(
                defaultBlockState()
                        .setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, Direction.NORTH)
                        .setValue(BlockStateProperties.WATERLOGGED, false)
        );
    }

    @Override
    @SuppressWarnings("deprecation")
    @ParametersAreNonnullByDefault
    @Nonnull
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Shapes.block();
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(JSGProperties.FACING_HORIZONTAL_PROPERTY);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState()
                .setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, ctx.getHorizontalDirection().getOpposite())
                .setValue(BlockStateProperties.WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, BlockPosHelper.rotateDir(blockState.getValue(JSGProperties.FACING_HORIZONTAL_PROPERTY), rotation));
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, BlockPosHelper.flipDir(blockState.getValue(JSGProperties.FACING_HORIZONTAL_PROPERTY), mirror));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable BlockGetter blockGetter, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        ItemHelper.applyGenericToolTip(this.getDescriptionId(), components, tooltipFlag);
    }

    @Override
    public void onWrenchUse(BlockState blockState, UseOnContext useOnContext) {
        this.rotate(blockState, Rotation.CLOCKWISE_90);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DestinyBearingBE(pPos, pState);
    }

    @Override
    public boolean renderHighlight(BlockState blockState) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public List<RegistryObject<CreativeModeTab>> getTabs() {
        return List.of(TabRegistry.TAB_DESTINY, dev.tauri.jsg.registry.TabRegistry.TAB_TRANSPORTATION);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
        if (level.isClientSide) return;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void playerWillDestroy(Level level, BlockPos pos, BlockState blockState, Player player) {
        super.playerWillDestroy(level, pos, blockState, player);
        if (level.isClientSide) return;
        var be = level.getBlockEntity(pos);
        if (!(be instanceof DestinyBearingBE bearing)) return;
        if (bearing.gateBasePos == null) return;
        var gBE = level.getBlockEntity(bearing.gateBasePos);
        if (!(gBE instanceof Stargate<?, ?> gate)) return;
        gate.getListenerHandler().removeListener(bearing);
        bearing.gateBasePos = null;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        super.wasExploded(level, pos, explosion);
        if (level.isClientSide) return;
    }

    @Override
    public JSGBlockItem getItemBlock() {
        return new DestinyBearingItem(this);
    }
}
