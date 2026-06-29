package dev.tauri.jsgdestiny;

import dev.tauri.jsg.core.JSGAddon;
import dev.tauri.jsg.core.JSGAddons;
import dev.tauri.jsg.core.LoggerWrapper;
import dev.tauri.jsg.core.common.registry.helper.RegistryHelper;
import dev.tauri.jsgdestiny.client.ClientConstants;
import dev.tauri.jsgdestiny.common.registry.JSGDestinyRegistriesInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Mod(JSGDestiny.MOD_ID)
public class JSGDestiny implements JSGAddon {
    public static final String MOD_ID = "jsg_destiny";
    public static final String MOD_NAME = "JSG: Destiny Addon";
    public static LoggerWrapper logger;

    public static String MOD_VERSION = "";
    public static final String MC_VERSION = "1.20.1";

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(JSGDestiny.MOD_ID);

    public JSGDestiny() {
        logger = new LoggerWrapper("[jsg destiny] ", LoggerFactory.getLogger(MOD_NAME));

        ModList.get().getModContainerById(MOD_ID).ifPresentOrElse(container -> MOD_VERSION = MC_VERSION + "-" + container.getModInfo().getVersion().getQualifier(), () -> {
        });
        JSGDestiny.logger.info("Loading {} version {}", JSGDestiny.MOD_NAME, JSGDestiny.MOD_VERSION);

        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        JSGDestinyRegistriesInit.init();

        JSGDestinyRegistriesInit.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

        JSGAddons.registerAddon(this);
    }

    @Override
    @NotNull
    public String getId() {
        return MOD_ID;
    }

    @Override
    @NotNull
    public Optional<LoggerWrapper> getLoggerWrapper() {
        return Optional.of(logger);
    }

    @Override
    public String @NotNull [] getWelcomeLogo() {
        return new String[]{
                "░░░░░██╗░██████╗░██████╗░██╗██████╗░███████╗░██████╗████████╗██╗███╗░░██╗██╗░░░██╗",
                "░░░░░██║██╔════╝██╔════╝░╚═╝██╔══██╗██╔════╝██╔════╝╚══██╔══╝██║████╗░██║╚██╗░██╔╝",
                "░░░░░██║╚█████╗░██║░░██╗░░░░██║░░██║█████╗░░╚█████╗░░░░██║░░░██║██╔██╗██║░╚████╔╝░",
                "██╗░░██║░╚═══██╗██║░░╚██╗░░░██║░░██║██╔══╝░░░╚═══██╗░░░██║░░░██║██║╚████║░░╚██╔╝░░",
                "╚█████╔╝██████╔╝╚██████╔╝██╗██████╔╝███████╗██████╔╝░░░██║░░░██║██║░╚███║░░░██║░░░",
                "░╚════╝░╚═════╝░░╚═════╝░╚═╝╚═════╝░╚══════╝╚═════╝░░░░╚═╝░░░╚═╝╚═╝░░╚══╝░░░╚═╝░░░"
        };
    }

    @Override
    public void onJSGCoreLoad() {
        ClientConstants.load();
    }
}
