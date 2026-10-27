package dev.tauri.jsgdestiny.common.item;

import dev.tauri.jsgcore.client.renderer.AbstractItemBEWLR;
import dev.tauri.jsgcore.common.item.JSGBlockItem;
import dev.tauri.jsgdestiny.client.renderer.item.DestinyBearingBEWLR;
import dev.tauri.jsgdestiny.common.block.DestinyBearingBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

public class DestinyBearingItem extends JSGBlockItem {
    public DestinyBearingItem(DestinyBearingBlock pBlock) {
        super(pBlock, new Properties(), pBlock.getTabs());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        rawBlock.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(AbstractItemBEWLR.create(DestinyBearingBEWLR::new));
    }
}
