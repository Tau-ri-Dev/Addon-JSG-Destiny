package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsg.core.common.item.JSGItem;
import dev.tauri.jsg.core.common.registry.CoreTabs;
import dev.tauri.jsgdestiny.Constants;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class JSGDestinyItems {
    public static final RegistryObject<JSGItem> UNIVERSAL_BULB = Constants.ITEM_HELPER.builder("universal_light_bulb").clearTooltip().setInTabs(List.of(JSGDestinyTabs.TAB_DESTINY, CoreTabs.TAB_RESOURCES)).buildGeneric();
    public static final RegistryObject<JSGItem> BEARING_BALL = Constants.ITEM_HELPER.builder("bearing_ball").clearTooltip().setInTabs(List.of(JSGDestinyTabs.TAB_DESTINY, CoreTabs.TAB_RESOURCES)).buildGeneric();

    public static void init() {
    }
}
