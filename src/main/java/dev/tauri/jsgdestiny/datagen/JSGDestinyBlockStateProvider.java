package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsgcore.JSGCore;
import dev.tauri.jsgcore.datagen.CoreBlockStateProvider;
import dev.tauri.jsgcore.mapping.JSGMapping;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class JSGDestinyBlockStateProvider extends CoreBlockStateProvider {
    public JSGDestinyBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JSGDestiny.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //BEARINGS
        models().getBuilder("bearing_generic").texture("particle", JSGMapping.rl(JSGCore.MOD_ID, "block/materials/naquadah_block"));
        JSGDestinyBlocks.DESTINY_BEARING.forEach((color, regObj) ->
            createObjModelBlock(regObj, "bearing_generic", null)
        );

        //FLOOR CHEVRONS
        models().getBuilder("floor_chevron_generic").texture("particle", JSGMapping.rl(JSGCore.MOD_ID, "block/materials/naquadah_block"));
        JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.forEach((color, regObj) -> {
            createObjModelBlock(regObj, "floor_chevron_generic", null);
        });
    }
}
