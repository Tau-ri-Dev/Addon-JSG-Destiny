package dev.tauri.jsgdestiny.common.expansion;

import dev.tauri.jsg.stargate.ScheduledTaskType;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.resources.ResourceLocation;

public class ScheduledTasksRegistry {
    public static final ScheduledTaskType DESTINY_BEARING_RENDER_UPDATE = new ScheduledTaskType(new ResourceLocation(JSGDestiny.MOD_ID, "destiny_bearing_render_update"), -1);

    public static void load(){}
}
