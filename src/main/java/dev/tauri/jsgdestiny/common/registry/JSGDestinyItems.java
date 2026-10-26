package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgcore.common.item.JSGItem;
import dev.tauri.jsgcore.common.registry.CoreTabs;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdestiny.Constants;

import java.util.List;

public class JSGDestinyItems {
    public static final RegistryObjectWrapper<JSGItem> UNIVERSAL_BULB = Constants.ITEM_HELPER.builder("universal_light_bulb").clearTooltip().setInTabs(List.of(() -> JSGDestinyTabs.TAB_DESTINY, () -> CoreTabs.TAB_RESOURCES)).buildGeneric();
    public static final RegistryObjectWrapper<JSGItem> BEARING_BALL = Constants.ITEM_HELPER.builder("bearing_ball").clearTooltip().setInTabs(List.of(() -> JSGDestinyTabs.TAB_DESTINY, () -> CoreTabs.TAB_RESOURCES)).buildGeneric();

    public static void init() {
    }
}
