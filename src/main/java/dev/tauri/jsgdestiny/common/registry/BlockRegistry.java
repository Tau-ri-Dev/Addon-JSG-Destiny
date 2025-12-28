package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsg.api.block.util.IItemBlock;
import dev.tauri.jsg.api.item.ITabbedItem;
import dev.tauri.jsg.api.item.JSGBlockItem;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.common.block.DestinyBearingBlock;
import dev.tauri.jsgdestiny.common.block.DestinyFloorChevronBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class BlockRegistry {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, JSGDestiny.MOD_ID);

    public static final RegistryObject<Block> DESTINY_BEARING = REGISTER.register("destiny_bearing", DestinyBearingBlock::new);
    public static final RegistryObject<Block> DESTINY_FLOOR_CHEVRON = REGISTER.register("destiny_floor_chevron", DestinyFloorChevronBlock::new);


    @SuppressWarnings("all")
    public static void register(IEventBus bus) {
        for (RegistryObject<Block> i : REGISTER.getEntries().stream().toList()) {
            ItemRegistry.REGISTER.register(i.getId().getPath(),
                    () -> {
                        List<RegistryObject<CreativeModeTab>> tabs = List.of();
                        if (i.get() instanceof ITabbedItem t) {
                            tabs = t.getTabs();
                        }
                        if (i.get() instanceof IItemBlock itemBlock)
                            return itemBlock.getItemBlock();
                        return new JSGBlockItem(i.get(), new Item.Properties(), tabs);
                    });
        }
        REGISTER.register(bus);
    }
}
