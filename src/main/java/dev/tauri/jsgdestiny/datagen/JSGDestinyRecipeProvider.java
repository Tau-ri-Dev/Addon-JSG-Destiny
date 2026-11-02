package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsg.common.registry.JSGItems;
import dev.tauri.jsgcore.common.definition.material.MaterialType;
import dev.tauri.jsgcore.datagen.CoreRecipeProvider;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.crypto.spec.RC2ParameterSpec;
import java.util.function.Consumer;

public class JSGDestinyRecipeProvider extends CoreRecipeProvider {
    public JSGDestinyRecipeProvider(PackOutput pOutput) { super (pOutput); }

    @ParametersAreNonnullByDefault
    protected void buildRecipe(Consumer<FinishedRecipe> pWriter) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, JSGDestinyItems.UNIVERSAL_BULB.get())
                .group(JSGDestiny.MOD_ID + ":" + JSGDestinyItems.UNIVERSAL_BULB.getId().getPath())
                .pattern(" P ")
                .pattern("PNP")
                .pattern(" T ")
                .define('P', Tags.Items.GLASS_PANES_COLORLESS)
                .define('N', Tags.Items.NUGGETS_GOLD)
                .define('T', MaterialType.TITANIUM.forgeTags().ingots())
                .unlockedBy("has_glass_pane", has(Tags.Items.GLASS_PANES_COLORLESS))
                .unlockedBy("has_gold_nugget", has(Tags.Items.NUGGETS_GOLD))
                .unlockedBy("has_titanium_igot", has(MaterialType.TITANIUM.forgeTags().ingots()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, JSGDestinyItems.BEARING_BALL.get())
                .group(JSGDestiny.MOD_ID + ":" + JSGDestinyItems.BEARING_BALL.getId().getPath())
                .pattern("III")
                .pattern("PBP")
                .pattern("PPP")
                .define('I', MaterialType.NAQUADAH_ALLOY.forgeTags().ingots())
                .define('P', Tags.Items.GLASS_PANES_WHITE)
                .define('B', JSGDestinyItems.UNIVERSAL_BULB.get())
                .unlockedBy("has_naquadah_ingot", has(MaterialType.NAQUADAH_ALLOY.forgeTags().ingots()))
                .unlockedBy("has_glass_pane", has(Tags.Items.GLASS_PANES_WHITE))
                .unlockedBy("has_bulb", has(JSGDestinyItems.BEARING_BALL.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.get())
                .group(JSGDestiny.MOD_ID + ":" + JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.getId().getPath())
                .pattern("CTC")
                .pattern("PBP")
                .pattern("TPT")
                .define('C', Tags.Items.INGOTS_COPPER)
                .define('T', MaterialType.TITANIUM.forgeTags().ingots())
                .define('P', Tags.Items.GLASS_PANES_WHITE)
                .define('B', JSGDestinyItems.UNIVERSAL_BULB.get())
                .unlockedBy("has_glass_pane", has(Tags.Items.GLASS_PANES_WHITE))
                .unlockedBy("has_copper_ingot", has(Tags.Items.INGOTS_COPPER))
                .unlockedBy("has_titanium_ingot", has(MaterialType.TITANIUM.forgeTags().ingots()))
                .unlockedBy("has_bulb", has(JSGDestinyItems.BEARING_BALL.get()))
                .save(pWriter);

    }
}
