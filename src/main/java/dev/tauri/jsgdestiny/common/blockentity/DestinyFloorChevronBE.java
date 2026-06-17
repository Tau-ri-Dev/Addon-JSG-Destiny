package dev.tauri.jsgdestiny.common.blockentity;

import dev.tauri.jsg.api.config.JSGConfig;
import dev.tauri.jsg.api.stargate.Stargate;
import dev.tauri.jsg.common.registry.tags.JSGBlockTags;
import dev.tauri.jsg.core.common.helper.LinkingHelper;
import dev.tauri.jsg.core.common.symbol.SymbolInterface;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class DestinyFloorChevronBE extends DestinyBearingBE {
    public DestinyFloorChevronBE(BlockPos pPos, BlockState pBlockState) {
        super(JSGDestinyBlockEntities.DESTINY_FLOOR_CHEVRON.get(), pPos, pBlockState);
    }

    @Override
    public Stargate<?> getLinkableStargate() {
        if (getLevel() == null) return null;
        return LinkingHelper.findClosestTile(getLevel(), getBlockPos(), JSGBlockTags.ALL_STARGATE_BASES, Stargate.class, JSGConfig.DialHomeDevice.rangeFlat.get(), JSGConfig.DialHomeDevice.rangeVertical.get());
    }

    @Override
    public void gateSymbolEngage(SymbolInterface symbol) {
    }
}
