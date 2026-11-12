package dev.tauri.jsgdestiny.common.registry.tags;

import dev.tauri.jsgcore.mapping.JSGMapping;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class JSGDestinyItemTags {
    public static final TagKey<Item> DESTINY_BEARINGS = tag("destiny_bearing");
    public static final TagKey<Item> DESTINY_FLOOR_CHEVRONS = tag("destiny_floor_chevron");
    public static final TagKey<Item> BEARING_BALLS = tag("bearing_ball");


    private static TagKey<Item> tag(String name) {
        return ItemTags.create(JSGMapping.rl(JSGDestiny.MOD_ID, name));
    }
}
