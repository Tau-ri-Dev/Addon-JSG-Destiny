package dev.tauri.jsgdestiny.common.blockentity;

import dev.tauri.jsg.blockentity.stargate.StargateAbstractBaseBE;
import dev.tauri.jsg.config.JSGConfig;
import dev.tauri.jsg.helpers.LinkingHelper;
import dev.tauri.jsg.registry.TagsRegistry;
import dev.tauri.jsg.stargate.network.SymbolInterface;
import dev.tauri.jsgdestiny.common.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class DestinyFloorChevronBE extends DestinyBearingBE {
    public DestinyFloorChevronBE(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.DESTINY_FLOOR_CHEVRON.get(), pPos, pBlockState);
    }

    @Override
    public StargateAbstractBaseBE getLinkableStargate() {
        if (getLevel() == null) return null;
        return LinkingHelper.findClosestTile(getLevel(), getBlockPos(), TagsRegistry.ALL_STARGATE_BASES, StargateAbstractBaseBE.class, JSGConfig.DialHomeDevice.rangeFlat.get(), JSGConfig.DialHomeDevice.rangeVertical.get());
    }

    @Override
    public void gateSymbolEngage(SymbolInterface symbol) {
    }
}
