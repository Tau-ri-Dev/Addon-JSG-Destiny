package dev.tauri.jsgdestiny.datagen.loot;

import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgcore.datagen.loot.CoreBlockLootTables;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class JSGDestinyBlockLootTables extends CoreBlockLootTables {

    public JSGDestinyBlockLootTables() {super ();}

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
