package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsg.api.JSGApi;
import dev.tauri.jsgcore.client.model.JSGOBJModelLoaderBuilder;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgcore.mapping.JSGMapping;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static java.util.Objects.requireNonNull;
import java.util.List;

public class JSGDestinyItemModelProvider extends ItemModelProvider {
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
                    .texture("layer0", JSGMapping.rl(JSGDestiny.MOD_ID, "item/"+ path));
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

    private void blockOBJModel(RegistryObjectWrapper<? extends Block> block, ItemDisplayContext... renderTypes) {
        itemOBJModel(requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())), renderTypes);
    }

    private void itemOBJModel(RegistryObjectWrapper<? extends Item> item, ItemDisplayContext... renderTypes) {
        itemOBJModel(requireNonNull(ForgeRegistries.ITEMS.getKey(item.get())), renderTypes);
    }

    private void itemOBJModel(ResourceLocation item, ItemDisplayContext... renderTypes) {
        getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", JSGMapping.rl(JSGDestiny.MOD_ID, "block/wip"))
                .customLoader((parent, existingFileHelper) -> new JSGOBJModelLoaderBuilder<>(parent, existingFileHelper).renderTypes(renderTypes))
                .visibility("override_transformations", true);
    }
}
