package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.block.DestinyBearingBlock;
import dev.tauri.jsgdestiny.common.block.DestinyFloorChevronBlock;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class JSGDestinyBlocks {
    public static final Map<DyeColor, RegistryObjectWrapper<DestinyBearingBlock>> DESTINY_BEARING = Util.make(new HashMap<>(), map -> {
        for (var color : DyeColor.values()) {
            map.put(color, JSGDestiny.REGISTRY_HELPER.block().register(color.getName() + "_destiny_bearing", () -> new DestinyBearingBlock(color.getTextColor() | (0xFF000000))));
        }
    });
    public static final Map<DyeColor, RegistryObjectWrapper<DestinyBearingBlock>> DESTINY_FLOOR_CHEVRON = Util.make(new HashMap<>(), map -> {
        for (var color : DyeColor.values()) {
            map.put(color, JSGDestiny.REGISTRY_HELPER.block().register(color.getName() + "_destiny_floor_chevron", () -> new DestinyFloorChevronBlock(color.getTextColor() | (0xFF000000))));
        }
    });

    public static void init() {
    }
}
