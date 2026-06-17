package dev.tauri.jsgdestiny.common.item;

import dev.tauri.jsg.core.client.renderer.AbstractItemBEWLR;
import dev.tauri.jsg.core.common.item.JSGBlockItem;
import dev.tauri.jsgdestiny.client.renderer.item.DestinyBearingBEWLR;
import dev.tauri.jsgdestiny.common.block.DestinyBearingBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

public class DestinyBearingItem extends JSGBlockItem {
    public DestinyBearingItem(DestinyBearingBlock pBlock) {
        super(pBlock, new Properties(), pBlock.getTabs());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        rawBlock.appendHoverText(stack, level, components, tooltipFlag);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(AbstractItemBEWLR.create(DestinyBearingBEWLR::new));
    }
}
