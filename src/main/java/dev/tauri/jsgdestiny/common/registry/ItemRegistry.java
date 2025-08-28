package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsg.helpers.TabHelper;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, JSGDestiny.MOD_ID);


    public static void register(IEventBus bus) {
        TabHelper.indexItemRegistry(() -> REGISTER);
        REGISTER.register(bus);
    }
}
