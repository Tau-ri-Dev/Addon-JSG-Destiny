package dev.tauri.jsgdestiny.common.item;

import dev.tauri.jsgcore.common.item.IColoredItem;
import dev.tauri.jsgcore.common.item.JSGItem;
import dev.tauri.jsgcore.common.registry.CoreTabs;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyTabs;

import java.util.List;

public class BearingBallItem extends JSGItem implements IColoredItem {
    public final int color;

    public BearingBallItem(int color) {
        super(new Properties(), List.of(() -> JSGDestinyTabs.TAB_DESTINY, () -> CoreTabs.TAB_RESOURCES));
        this.color = color;
    }

    @Override
    public int getRGBColor() {
        return color;
    }
}
