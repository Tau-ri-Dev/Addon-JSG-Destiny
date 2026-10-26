package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgcore.common.registry.helper.TabBuilder;
import dev.tauri.jsgcore.mapping.JSGMapping;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.world.item.CreativeModeTab;

public class JSGDestinyTabs {
    public static final RegistryObjectWrapper<CreativeModeTab> TAB_DESTINY = JSGDestiny.REGISTRY_HELPER.tab().register("destiny", TabBuilder.create(JSGMapping.rl(JSGDestiny.MOD_ID, "destiny")).withIcon(() -> JSGDestinyBlocks.DESTINY_BEARING).build());

    public static void init() {
    }
}
