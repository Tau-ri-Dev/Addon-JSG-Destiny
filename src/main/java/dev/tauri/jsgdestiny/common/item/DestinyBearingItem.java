package dev.tauri.jsgdestiny.common.item;

import dev.tauri.jsg.api.item.JSGBlockItem;
import dev.tauri.jsg.item.JSGModelOBJInGUIRenderer;
import dev.tauri.jsgdestiny.client.ModelsHolder;
import dev.tauri.jsgdestiny.common.block.DestinyBearingBlock;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
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
        consumer.accept(new IClientItemExtensions() {
            private static final JSGModelOBJInGUIRenderer instance = new JSGModelOBJInGUIRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                instance.renderPartInterface = getRenderPartInterface();
                return instance;
            }
        });
    }

    @OnlyIn(Dist.CLIENT)
    public JSGModelOBJInGUIRenderer.RenderPartInterface getRenderPartInterface() {
        return (itemStack, itemDisplayContext, stack, bufferSource, light, overlay) -> {
            stack.pushPose();
            stack.translate(0, 0.4f, 0);
            stack.scale(2.5f, 2.5f, 2.5f);
            ModelsHolder.DESTINY_BEARING_BODY.bindTexture().render(stack, bufferSource, light, overlay);
            ModelsHolder.DESTINY_BEARING_LIGHT.bindTexture().render(stack, bufferSource, light, overlay);
            stack.popPose();
        };
    }
}
