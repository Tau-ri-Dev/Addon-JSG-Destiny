package dev.tauri.jsgdestiny.datagen.tags;

import dev.tauri.jsgcore.common.registry.tag.CoreBlockTags;
import dev.tauri.jsgcore.datagen.tag.CoreBlockTagGenerator;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import dev.tauri.jsgdestiny.common.registry.tags.JSGDestinyBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

public class JSGDestinyBlockTagGenerator extends CoreBlockTagGenerator {
    public JSGDestinyBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, JSGDestiny.MOD_ID, existingFileHelper);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void addTags(HolderLookup.Provider pProvider) {

        var placeable_accessories = tag(JSGDestinyBlockTags.PLACEABLE_ACCESSORIES);
        var floor_accessories = tag(JSGDestinyBlockTags.FLOOR_ACCESSORIES);
        var stargate_accessories = tag(JSGDestinyBlockTags.STARGATE_ACCESSORIES);


        //JSGDestiny Tags
        var bearingTag = tag(JSGDestinyBlockTags.DESTINY_BEARINGS);

        JSGDestinyBlocks.DESTINY_BEARING.forEach((color, regObj) -> {
            bearingTag.add(regObj.get());
        });
        placeable_accessories.addTag(JSGDestinyBlockTags.DESTINY_BEARINGS);
        stargate_accessories.addTag(JSGDestinyBlockTags.DESTINY_BEARINGS);


        tag(JSGDestinyBlockTags.DESTINY_FLOOR_CHEVRONS).add(JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.get());
        /*var floorChevronTag = tag(JSGDestinyBlockTags.DESTINY_FLOOR_CHEVRONS;
        JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.forEach((color, regObj) -> {
            floorChevronTag.add(regObj.get());
        });*/
        floor_accessories.addTag(JSGDestinyBlockTags.DESTINY_FLOOR_CHEVRONS);
        stargate_accessories.addTag(JSGDestinyBlockTags.DESTINY_FLOOR_CHEVRONS);

        //AFTER WE ADD IT...
        //tag(JSGDestinyBlockTags.RAMPS);


        //CORE TAGS
        tag(CoreBlockTags.BOSS_IMMUNE)
                .addTag(JSGDestinyBlockTags.STARGATE_ACCESSORIES);

        //MINECRAFT TAGS
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(JSGDestinyBlockTags.STARGATE_ACCESSORIES);
        tag(BlockTags.NEEDS_DIAMOND_TOOL).addTag(JSGDestinyBlockTags.STARGATE_ACCESSORIES);

        tag(BlockTags.INVALID_SPAWN_INSIDE).addTag(JSGDestinyBlockTags.STARGATE_ACCESSORIES);
        tag(BlockTags.VIBRATION_RESONATORS).addTag(JSGDestinyBlockTags.STARGATE_ACCESSORIES);
        tag(BlockTags.INSIDE_STEP_SOUND_BLOCKS).addTag(JSGDestinyBlockTags.FLOOR_ACCESSORIES);

    }
}
