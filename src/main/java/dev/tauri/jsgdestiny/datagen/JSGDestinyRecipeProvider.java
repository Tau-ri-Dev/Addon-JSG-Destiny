package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsgcore.common.definition.material.MaterialType;
import dev.tauri.jsgcore.datagen.CoreRecipeProvider;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyBlocks;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

public class JSGDestinyRecipeProvider extends CoreRecipeProvider {
    public JSGDestinyRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

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

        for (DyeColor color : DyeColor.values()) {
            var coloredBall = JSGDestinyItems.BEARING_BALLS.get(color).get();
            var glassPaneTag = getGlassPane(color);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, coloredBall)
                    .group(JSGDestiny.MOD_ID + ":bearing_balls")
                    .pattern("III")
                    .pattern("PBP")
                    .pattern("PPP")
                    .define('I', MaterialType.NAQUADAH_ALLOY.forgeTags().ingots())
                    .define('P', glassPaneTag)
                    .define('B', JSGDestinyItems.UNIVERSAL_BULB.get())
                    .unlockedBy("has_naquadah_ingot", has(MaterialType.NAQUADAH_ALLOY.forgeTags().ingots()))
                    .unlockedBy("has_glass_pane", has(glassPaneTag))
                    .unlockedBy("has_bulb", has(JSGDestinyItems.UNIVERSAL_BULB.get()))
                    .save(pWriter);

            var coloredBearing = JSGDestinyBlocks.DESTINY_BEARING.get(color).get();

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, coloredBearing)
                    .group(JSGDestiny.MOD_ID + ":bearings")
                    .pattern("TTT")
                    .pattern("NRN")
                    .pattern(" B ")
                    .define('T', MaterialType.TITANIUM.forgeTags().ingots())
                    .define('N', MaterialType.NAQUADAH.forgeTags().ingots())
                    .define('R', MaterialType.NAQUADAH_ALLOY.forgeTags().ingots())
                    .define('B', coloredBall)
                    .unlockedBy("has_titanium_ingot", has(MaterialType.TITANIUM.forgeTags().ingots()))
                    .unlockedBy("has_naquadah_ingot", has(MaterialType.NAQUADAH.forgeTags().ingots()))
                    .unlockedBy("has_naquadah_alloy_ingot", has(MaterialType.NAQUADAH_ALLOY.forgeTags().ingots()))
                    .unlockedBy("has_ball", has(coloredBall))
                    .save(pWriter);

            var coloredFloorChevrons = JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON.get(color).get();

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, coloredFloorChevrons)
                    .group(JSGDestiny.MOD_ID + ":floor_chevrons")
                    .pattern("CTC")
                    .pattern("GBG")
                    .pattern("TGT")
                    .define('C', Tags.Items.INGOTS_COPPER)
                    .define('T', MaterialType.TITANIUM.forgeTags().ingots())
                    .define('G', glassPaneTag)
                    .define('B', JSGDestinyItems.UNIVERSAL_BULB.get())
                    .unlockedBy("has_copper_ingot", has(Tags.Items.INGOTS_COPPER))
                    .unlockedBy("has_titanium_ingot", has(MaterialType.TITANIUM.forgeTags().ingots()))
                    .unlockedBy("has_glass_pane", has(glassPaneTag))
                    .unlockedBy("has_light_bulb", has(JSGDestinyItems.UNIVERSAL_BULB.get()))
                    .save(pWriter);
        }

    }

    private TagKey<Item> getGlassPane(DyeColor color) {
        return switch (color) {
            case BLACK -> Tags.Items.GLASS_PANES_BLACK;
            case BLUE -> Tags.Items.GLASS_PANES_BLUE;
            case BROWN -> Tags.Items.GLASS_PANES_BROWN;
            case CYAN -> Tags.Items.GLASS_PANES_CYAN;
            case GRAY -> Tags.Items.GLASS_PANES_GRAY;
            case GREEN -> Tags.Items.GLASS_PANES_GREEN;
            case LIGHT_BLUE -> Tags.Items.GLASS_PANES_LIGHT_BLUE;
            case LIGHT_GRAY -> Tags.Items.GLASS_PANES_LIGHT_GRAY;
            case LIME -> Tags.Items.GLASS_PANES_LIME;
            case MAGENTA -> Tags.Items.GLASS_PANES_MAGENTA;
            case ORANGE -> Tags.Items.GLASS_PANES_ORANGE;
            case PINK -> Tags.Items.GLASS_PANES_PINK;
            case PURPLE -> Tags.Items.GLASS_PANES_PURPLE;
            case RED -> Tags.Items.GLASS_PANES_RED;
            case WHITE -> Tags.Items.GLASS_PANES_WHITE;
            case YELLOW -> Tags.Items.GLASS_PANES_YELLOW;
        };
    }
}
