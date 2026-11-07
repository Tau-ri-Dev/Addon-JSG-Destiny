package dev.tauri.jsgdestiny.datagen.tags;

import dev.tauri.jsg.JSG;
import dev.tauri.jsgcore.datagen.tag.CoreItemTagGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

public class JSGDestinyItemTagGenerator extends CoreItemTagGenerator {
    public JSGDestinyItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, JSG.MOD_ID, existingFileHelper);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void addTags(HolderLookup.Provider pProvider) {
    }
}
