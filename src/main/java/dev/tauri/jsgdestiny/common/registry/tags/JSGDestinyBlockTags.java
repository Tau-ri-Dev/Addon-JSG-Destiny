package dev.tauri.jsgdestiny.common.registry.tags;

import dev.tauri.jsgcore.mapping.JSGMapping;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class JSGDestinyBlockTags {
    public static final TagKey<Block> DESTINY_BEARINGS = tag("destiny_bearing");
    public static final TagKey<Block> DESTINY_FLOOR_CHEVRONS = tag("destiny_floor_chevron");

    public static final TagKey<Block> PLACEABLE_ACCESSORIES = tag("placeable_stargate_accessories");
    public static final TagKey<Block> FLOOR_ACCESSORIES = tag("floor_stargate_accessories");
    public static final TagKey<Block> STARGATE_ACCESSORIES = tag("stargate_accessories");

    public static final TagKey<Block> RAMPS = tag("ramps");

    private static TagKey<Block> tag(String name) {return BlockTags.create(JSGMapping.rl(JSGDestiny.MOD_ID, name));}
}
