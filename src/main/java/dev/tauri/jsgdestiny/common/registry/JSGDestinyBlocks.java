package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.block.DestinyBearingBlock;
import dev.tauri.jsgdestiny.common.block.DestinyFloorChevronBlock;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class JSGDestinyBlocks {
    public static final Map<DyeColor, RegistryObjectWrapper<DestinyBearingBlock>> DESTINY_BEARING = Util.make(new HashMap<>(), map -> {
        for (var color : DyeColor.values()) {
            map.put(color, JSGDestiny.REGISTRY_HELPER.block().register(color.getName() + "_destiny_bearing", () -> new DestinyBearingBlock(color.getTextColor() | (0xFF000000))));
        }
    });
    public static final RegistryObjectWrapper<Block> DESTINY_FLOOR_CHEVRON = JSGDestiny.REGISTRY_HELPER.block().register("destiny_floor_chevron", DestinyFloorChevronBlock::new);

    public static void init() {
    }
}
