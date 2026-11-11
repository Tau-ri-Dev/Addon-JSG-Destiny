package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.datagen.loot.JSGDestinyLootTableProvider;
import dev.tauri.jsgdestiny.datagen.tags.JSGDestinyBlockTagGenerator;
import dev.tauri.jsgdestiny.datagen.tags.JSGDestinyItemTagGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = JSGDestiny.MOD_ID)
public class JSGDestinyDataGenerators {
    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var exFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new JSGDestinyRecipeProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), JSGDestinyLootTableProvider.create(output, lookupProvider));

        generator.addProvider(event.includeClient(), new JSGDestinyBlockStateProvider(output, exFileHelper));
        generator.addProvider(event.includeClient(), new JSGDestinyItemModelProvider(output, exFileHelper));

        var blockTagGenerator = generator.addProvider(event.includeServer(), new JSGDestinyBlockTagGenerator(output, lookupProvider, exFileHelper));
        generator.addProvider(event.includeServer(), new JSGDestinyItemTagGenerator(output, lookupProvider, blockTagGenerator.contentsGetter(), exFileHelper));

        generator.addProvider(event.includeClient(), new JSGDestinyLanguageProvider(output, "en_us"));

    }
}
