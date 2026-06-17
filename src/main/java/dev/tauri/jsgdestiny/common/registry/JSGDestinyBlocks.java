package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.block.DestinyBearingBlock;
import dev.tauri.jsgdestiny.common.block.DestinyFloorChevronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class JSGDestinyBlocks {
    public static final RegistryObject<Block> DESTINY_BEARING = JSGDestiny.REGISTRY_HELPER.block().register("destiny_bearing", DestinyBearingBlock::new);
    public static final RegistryObject<Block> DESTINY_FLOOR_CHEVRON = JSGDestiny.REGISTRY_HELPER.block().register("destiny_floor_chevron", DestinyFloorChevronBlock::new);

    public static void init() {
    }
}
