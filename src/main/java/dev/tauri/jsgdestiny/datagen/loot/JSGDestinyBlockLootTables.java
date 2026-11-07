package dev.tauri.jsgdestiny.datagen.loot;

import dev.tauri.jsg.api.JSGApi;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class JSGDestinyBlockLootTables extends BlockLootSubProvider {

    public JSGDestinyBlockLootTables() {super (Set.of(), FeatureFlags.REGISTRY.allFlags());}

    @Override
    protected void generate() {

        for (DyeColor color : DyeColor.values()) {
            dropSelf(JSGDestinyBlocks.DESTINY_BEARING.get(color).get());
            //dropSelf(JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.get(color).get());
        }
        dropSelf(JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.get());
    }

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        return JSGDestiny.REGISTRY_HELPER.block().getEntries().stream().map(RegistryObjectWrapper::get)::iterator;
    }
}
