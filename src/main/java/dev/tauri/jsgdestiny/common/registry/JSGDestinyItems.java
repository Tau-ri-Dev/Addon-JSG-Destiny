package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgcore.common.item.JSGItem;
import dev.tauri.jsgcore.common.registry.CoreTabs;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdestiny.Constants;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.item.BearingBallItem;
import net.minecraft.Util;
import net.minecraft.world.item.DyeColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSGDestinyItems {
    public static final RegistryObjectWrapper<JSGItem> UNIVERSAL_BULB = Constants.ITEM_HELPER.builder("universal_light_bulb")
            .clearTooltip().setInTabs(List.of(() -> JSGDestinyTabs.TAB_DESTINY, () -> CoreTabs.TAB_RESOURCES)).buildGeneric();

    public static final Map<DyeColor, RegistryObjectWrapper<BearingBallItem>> BEARING_BALLS = Util.make(new HashMap<>(), map -> {
        for (var color : DyeColor.values()) {
            map.put(color, JSGDestiny.REGISTRY_HELPER.item().register(color.getName() + "_bearing_ball", () -> new BearingBallItem(color.getTextColor() | (0xFF000000))));
        }
    });

    public static void init() {
    }
}
