package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsg.core.common.entity.ScheduledTaskType;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraftforge.registries.RegistryObject;

public class JSGDestinyScheduledTasks {
    public static final RegistryObject<ScheduledTaskType> DESTINY_BEARING_RENDER_UPDATE = JSGDestiny.REGISTRY_HELPER.scheduledTask().register("destiny_bearing_render_update", () -> new ScheduledTaskType("destiny_bearing_render_update", -1));

    public static void init() {
    }
}
