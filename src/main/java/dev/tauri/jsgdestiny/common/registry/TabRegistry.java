package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgdestiny.Constants;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JSGDestiny.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB_DESTINY = Constants.TAB_HELPER.createCreativeTabWithItemStack("destiny", () -> new ItemStack(BlockRegistry.DESTINY_BEARING.get()));


    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}
