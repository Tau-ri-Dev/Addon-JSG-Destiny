package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsg.api.item.JSGItem;
import dev.tauri.jsg.helpers.TabHelper;
import dev.tauri.jsgdestiny.Constants;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@SuppressWarnings("unused")
public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, JSGDestiny.MOD_ID);

    public static final RegistryObject<JSGItem> UNIVERSAL_BULB = Constants.ITEM_HELPER.builder("universal_light_bulb").clearTooltip().setInTabs(List.of(TabRegistry.TAB_DESTINY, dev.tauri.jsg.registry.TabRegistry.TAB_RESOURCES)).buildGeneric();
    public static final RegistryObject<JSGItem> BEARING_BALL = Constants.ITEM_HELPER.builder("bearing_ball").clearTooltip().setInTabs(List.of(TabRegistry.TAB_DESTINY, dev.tauri.jsg.registry.TabRegistry.TAB_RESOURCES)).buildGeneric();

    public static void register(IEventBus bus) {
        TabHelper.indexItemRegistry(() -> REGISTER);
        REGISTER.register(bus);
    }
}
