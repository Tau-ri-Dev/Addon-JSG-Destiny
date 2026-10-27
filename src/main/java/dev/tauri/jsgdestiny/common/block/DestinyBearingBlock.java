package dev.tauri.jsgdestiny.common.block;

import dev.tauri.jsg.api.stargate.Stargate;
import dev.tauri.jsgcore.common.block.TickableBEBlock;
import dev.tauri.jsgcore.common.block.util.IHighlightBlock;
import dev.tauri.jsgcore.common.block.util.IItemBlock;
import dev.tauri.jsgcore.common.block.util.WrenchRotatable;
import dev.tauri.jsgcore.common.blockstate.JSGProperties;
import dev.tauri.jsgcore.common.helper.BlockPosHelper;
import dev.tauri.jsgcore.common.helper.ItemHelper;
import dev.tauri.jsgcore.common.item.ITabbedItem;
import dev.tauri.jsgcore.common.item.JSGBlockItem;
import dev.tauri.jsgcore.common.registry.CoreTabs;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdestiny.common.blockentity.DestinyBearingBE;
import dev.tauri.jsgdestiny.common.item.DestinyBearingItem;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyTabs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Supplier;

public class DestinyBearingBlock extends TickableBEBlock implements IHighlightBlock, SimpleWaterloggedBlock, ITabbedItem, WrenchRotatable, IItemBlock {
    public DestinyBearingBlock() {
        super(Properties.ofFullCopy(Blocks.IRON_BLOCK).isViewBlocking((BlockState state, BlockGetter getter, BlockPos pos) -> false).noOcclusion());
        this.registerDefaultState(
                defaultBlockState()
                        .setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, Direction.NORTH)
                        .setValue(BlockStateProperties.WATERLOGGED, false)
        );
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
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
    public @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, BlockPosHelper.rotateDir(blockState.getValue(JSGProperties.FACING_HORIZONTAL_PROPERTY), rotation));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.setValue(JSGProperties.FACING_HORIZONTAL_PROPERTY, BlockPosHelper.flipDir(blockState.getValue(JSGProperties.FACING_HORIZONTAL_PROPERTY), mirror));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ItemHelper.applyGenericToolTip(this.getDescriptionId(), tooltipComponents, tooltipFlag);
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
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public List<Supplier<RegistryObjectWrapper<CreativeModeTab>>> getTabs() {
        if (CoreTabs.TAB_TRANSPORTATION.get() != null)
            return List.of(() -> JSGDestinyTabs.TAB_DESTINY, CoreTabs.TAB_TRANSPORTATION);
        return List.of(() -> JSGDestinyTabs.TAB_DESTINY);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
        if (level.isClientSide) return;
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState blockState, Player player) {
        var result = super.playerWillDestroy(level, pos, blockState, player);
        if (level.isClientSide) return result;
        var be = level.getBlockEntity(pos);
        if (!(be instanceof DestinyBearingBE bearing)) return result;
        if (bearing.gateBasePos == null) return result;
        var gBE = level.getBlockEntity(bearing.gateBasePos);
        if (!(gBE instanceof Stargate<?> gate)) return result;
        gate.getListenerHandler().removeListener(bearing);
        bearing.gateBasePos = null;
        return result;
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
