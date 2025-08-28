package dev.tauri.jsgdestiny;

import dev.tauri.jsg.JSG;
import dev.tauri.jsg.LoggerWrapper;
import dev.tauri.jsg.api.JSGAddon;
import dev.tauri.jsgdestiny.common.packet.JSGDPacketHandler;
import dev.tauri.jsgdestiny.common.registry.BlockEntityRegistry;
import dev.tauri.jsgdestiny.common.registry.BlockRegistry;
import dev.tauri.jsgdestiny.common.registry.ItemRegistry;
import dev.tauri.jsgdestiny.common.registry.TabRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(JSGDestiny.MOD_ID)
public class JSGDestiny implements JSGAddon {
    public static final String MOD_ID = "jsg_destiny";
    public static final String MOD_NAME = "JSG: Destiny Addon";
    public static Logger logger;

    public static String MOD_VERSION = "";
    public static final String MC_VERSION = "1.20.1";

    public JSGDestiny() {
        logger = new LoggerWrapper("[jsg destiny] ", LoggerFactory.getLogger(MOD_NAME));

        ModList.get().getModContainerById(MOD_ID).ifPresentOrElse(container -> MOD_VERSION = MC_VERSION + "-" + container.getModInfo().getVersion().getQualifier(), () -> {
        });
        JSGDestiny.logger.info("Loading JSG:Destiny Addon version {}", JSGDestiny.MOD_VERSION);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        Constants.load();

        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        TabRegistry.register(modEventBus);
        BlockEntityRegistry.register(modEventBus);

        JSGDPacketHandler.init();

        JSG.registerAddon(this);
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }

    @Override
    public String getId() {
        return MOD_ID;
    }

    @Override
    public String getVersion() {
        return MOD_VERSION;
    }
}
