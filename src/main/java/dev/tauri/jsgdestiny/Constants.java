package dev.tauri.jsgdestiny;

import dev.tauri.jsg.helpers.TabHelper;
import dev.tauri.jsg.helpers.registry.item.ItemRegistryHelperGeneric;
import dev.tauri.jsgdestiny.common.registry.ItemRegistry;
import dev.tauri.jsgdestiny.common.registry.TabRegistry;

public class Constants {
    public static final ItemRegistryHelperGeneric ITEM_HELPER = new ItemRegistryHelperGeneric(() -> ItemRegistry.REGISTER);
    public static final TabHelper TAB_HELPER = new TabHelper(() -> TabRegistry.REGISTER);
}
