package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsgcore.datagen.CoreItemModelProvider;
import dev.tauri.jsgcore.mapping.JSGMapping;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

public class JSGDestinyItemModelProvider extends CoreItemModelProvider {
    public JSGDestinyItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JSGDestiny.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        withExistingParent(JSGDestinyItems.UNIVERSAL_BULB.getId().getPath(), "item/generated")
                .texture("layer0", JSGMapping.rl(JSGDestiny.MOD_ID, "item/" + JSGDestinyItems.UNIVERSAL_BULB.getId().getPath()));

        JSGDestinyItems.BEARING_BALLS.forEach((color, object) -> {
            var path = object.getId().getPath();

            withExistingParent(path, "item/generated")
                    .texture("layer0", JSGMapping.rl(JSGDestiny.MOD_ID, "item/" + path));
        });

        //All INVENTORY OBJ MODELS
        JSGDestinyBlocks.DESTINY_BEARING.forEach((color, object) -> {
            ItemDisplayContext[] allContext = ItemDisplayContext.values();

            blockOBJModel(object, allContext);

        });

        List.of(JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON
        ).forEach(regObj -> {
            ItemDisplayContext[] allContext = ItemDisplayContext.values();

            blockOBJModel(regObj, allContext);
        });

    }
}
