package dev.tauri.jsgdestiny.common.registry;

import dev.tauri.jsgcore.common.registry.helper.RegistryHelper;
import dev.tauri.jsgcore.common.registry.helper.RegistryObjectWrapper;
import dev.tauri.jsgdestiny.JSGDestiny;
import dev.tauri.jsgdestiny.client.renderer.DestinyFloorChevronRenderer;
import dev.tauri.jsgdestiny.client.renderer.bearing.DestinyBearingRenderer;
import dev.tauri.jsgdestiny.common.blockentity.DestinyBearingBE;
import dev.tauri.jsgdestiny.common.blockentity.DestinyFloorChevronBE;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;

public class JSGDestinyBlockEntities {
    public static final RegistryObjectWrapper<BlockEntityType<DestinyBearingBE>> DESTINY_BEARING = JSGDestiny.REGISTRY_HELPER.be().register("destiny_bearing", RegistryHelper.beSupplier(DestinyBearingBE::new, JSGDestinyBlocks.DESTINY_BEARING));
    public static final RegistryObjectWrapper<BlockEntityType<DestinyFloorChevronBE>> DESTINY_FLOOR_CHEVRON = JSGDestiny.REGISTRY_HELPER.be().register("destiny_floor_chevron", RegistryHelper.beSupplier(DestinyFloorChevronBE::new, JSGDestinyBlocks.DESTINY_FLOOR_CHEVRON));

    public static void init() {
        JSGDestiny.REGISTRY_HELPER.beRenderers(() -> List.of(
                new RegistryHelper.BlockEntityRendererPair<>(DESTINY_BEARING.get(), DestinyBearingRenderer::new),
                new RegistryHelper.BlockEntityRendererPair<>(DESTINY_FLOOR_CHEVRON.get(), DestinyFloorChevronRenderer::new)
        ));
    }
}
