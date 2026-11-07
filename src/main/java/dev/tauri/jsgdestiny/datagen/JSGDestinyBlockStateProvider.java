package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsgcore.JSGCore;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgcore.mapping.JSGMapping;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class JSGDestinyBlockStateProvider extends BlockStateProvider {
    public JSGDestinyBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JSGDestiny.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //BEARINGS
        models().getBuilder("bearing_generic").texture("particle", JSGMapping.rl(JSGCore.MOD_ID, "block/materials/naquadah_block"));
        JSGDestinyBlocks.DESTINY_BEARING.forEach((color, regObj) -> {
            createColoredObjModelBlock(regObj, "bearing_generic");
        });


        //FLOOR CHEVRONS
        models().getBuilder("floor_chevron_generic").texture("particle", JSGMapping.rl(JSGCore.MOD_ID, "block/materials/naquadah_block"));

        createColoredObjModelBlock(JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON, "floor_chevron_generic");
        /*JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.forEach((color, regObj) -> {
            createColoredObjModelBlock(regObj, "bearing_generic");
        });*/
    }

    private void createColoredObjModelBlock(RegistryObjectWrapper<? extends Block> block, String path) {
        models().withExistingParent(block.getId().getPath(), JSGMapping.rl(JSGDestiny.MOD_ID, "block/" + path));
        simpleBlock(block.get(), models().getExistingFile(JSGMapping.rl(JSGDestiny.MOD_ID, "block/" + path)));
    }
}
