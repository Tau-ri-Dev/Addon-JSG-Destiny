package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsgcore.JSGCore;
import dev.tauri.jsgcore.datagen.CoreLanguageProvider;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyItems;
import net.minecraft.data.PackOutput;

public class JSGDestinyLanguageProvider extends CoreLanguageProvider {
    public JSGDestinyLanguageProvider(PackOutput output, String locate) {
        super(output, JSGDestiny.MOD_ID, locate);
    }

    @Override
    protected void addTranslations() {
        //TABS
        add("itemGroup." + JSGDestiny.MOD_ID + ".destiny", "JSG: Destiny");

        //ITEMS - CRAFTING ELEMENTS
        addItem(JSGDestinyItems.UNIVERSAL_BULB);

        JSGDestinyItems.BEARING_BALLS.forEach((color, object) ->
            addItem(object));

        //BLOCKS
        JSGDestinyBlocks.DESTINY_BEARING.forEach((color, object) ->
                addBlock(object));

        addBlock(JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON);


        //GENERIC TOOLTIPS
        addCommonTooltip(JSGDestiny.MOD_ID, "destiny_bearing", "Lights up when engaging symbol or when the gate is active", "Place this block touching top side of the top gate's chevron%nl%to link with the gate.");
        addCommonTooltip(JSGDestiny.MOD_ID, "destiny_floor_chevron", "Lights up when the linked gate is active", "Place this block nearby gate%nl%to link with that gate.");
    }
}
