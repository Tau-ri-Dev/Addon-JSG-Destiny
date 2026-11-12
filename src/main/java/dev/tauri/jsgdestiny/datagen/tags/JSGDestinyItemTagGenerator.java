package dev.tauri.jsgdestiny.datagen.tags;

import dev.tauri.jsgcore.common.definition.material.MaterialType;
import dev.tauri.jsgcore.datagen.tag.CoreItemTagGenerator;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyItems;
import dev.tauri.jsgdestiny.common.registry.tags.JSGDestinyItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.IntersectionIngredient;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

public class JSGDestinyItemTagGenerator extends CoreItemTagGenerator {
    public JSGDestinyItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, JSGDestiny.MOD_ID, existingFileHelper);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void addTags(HolderLookup.Provider pProvider) {

        for (DyeColor color : DyeColor.values()) {
            var glassColorTag = getDyeColorTag(color);

            tag(glassColorTag)
                    .add(JSGDestinyBlocks.DESTINY_BEARING.get(color).get().asItem())
                    .add(JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.get(color).get().asItem())
                    .add(JSGDestinyItems.BEARING_BALLS.get(color).get());

            tag(JSGDestinyItemTags.DESTINY_BEARINGS).add(JSGDestinyBlocks.DESTINY_BEARING.get(color).get().asItem());
            tag(JSGDestinyItemTags.DESTINY_FLOOR_CHEVRONS).add(JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.get(color).get().asItem());
            tag(JSGDestinyItemTags.BEARING_BALLS).add(JSGDestinyItems.BEARING_BALLS.get(color).get());
        }
    }
}
