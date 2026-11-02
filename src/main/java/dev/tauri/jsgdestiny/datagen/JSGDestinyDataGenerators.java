package dev.tauri.jsgdestiny.datagen;

import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JSGDestiny.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JSGDestinyDataGenerators {
    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var exFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

    }
}
