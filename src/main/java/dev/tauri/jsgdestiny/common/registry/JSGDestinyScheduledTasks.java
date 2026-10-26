package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgcore.common.entity.ScheduledTaskType;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdestiny.JSGDestiny;

public class JSGDestinyScheduledTasks {
    public static final RegistryObjectWrapper<ScheduledTaskType> DESTINY_BEARING_RENDER_UPDATE = JSGDestiny.REGISTRY_HELPER.scheduledTask().register("destiny_bearing_render_update", () -> new ScheduledTaskType("destiny_bearing_render_update", -1));

    public static void init() {
    }
}
