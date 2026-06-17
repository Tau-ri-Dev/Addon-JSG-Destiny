package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsg.core.common.registry.helper.TabBuilder;
import dev.tauri.jsg.core.mapping.JSGMapping;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;

public class JSGDestinyTabs {
    public static final RegistryObject<CreativeModeTab> TAB_DESTINY = JSGDestiny.REGISTRY_HELPER.tab().register("destiny", TabBuilder.create(JSGMapping.rl(JSGDestiny.MOD_ID, "destiny")).withIcon(() -> JSGDestinyBlocks.DESTINY_BEARING).build());

    public static void init() {
    }
}
