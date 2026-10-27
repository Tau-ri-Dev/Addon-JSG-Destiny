package dev.tauri.jsgdestiny.common.item;

import dev.tauri.jsgcore.client.renderer.AbstractItemBEWLR;
import dev.tauri.jsgdestiny.client.renderer.item.DestinyFloorChevronBEWLR;
import dev.tauri.jsgdestiny.common.block.DestinyFloorChevronBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class DestinyFloorChevronItem extends DestinyBearingItem {
    public DestinyFloorChevronItem(DestinyFloorChevronBlock pBlock) {
        super(pBlock);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(AbstractItemBEWLR.create(DestinyFloorChevronBEWLR::new));
    }
}
