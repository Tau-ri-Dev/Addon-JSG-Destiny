package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraftforge.eventbus.api.IEventBus;

public class JSGDestinyRegistriesInit {
    public static void init() {
        JSGDestinyItems.init();
        JSGDestinyBlocks.init();
        JSGDestinyBlockEntities.init();
        JSGDestinyScheduledTasks.init();
        JSGDestinyTabs.init();
    }

    public static void register(IEventBus bus) {
        JSGDestiny.REGISTRY_HELPER.register(bus);
    }
}
