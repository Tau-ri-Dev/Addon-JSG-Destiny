package dev.tauri.jsgdestiny.common.item;

import com.mojang.math.Axis;
import dev.tauri.jsg.JSG;
import dev.tauri.jsg.item.JSGModelOBJInGUIRenderer;
import dev.tauri.jsg.loader.LoadersHolder;
import dev.tauri.jsgdestiny.client.ModelsHolder;
import dev.tauri.jsgdestiny.client.renderer.DestinyFloorChevronRenderer;
import dev.tauri.jsgdestiny.common.block.DestinyFloorChevronBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DestinyFloorChevronItem extends DestinyBearingItem {
    public DestinyFloorChevronItem(DestinyFloorChevronBlock pBlock) {
        super(pBlock);
    }

    @OnlyIn(Dist.CLIENT)
    public JSGModelOBJInGUIRenderer.RenderPartInterface getRenderPartInterface() {
        return (itemStack, itemDisplayContext, stack, bufferSource, light, overlay) -> {
            stack.pushPose();
            // TODO: Fix transparency from the other side

            stack.translate(0, 0.4, 0);
            stack.mulPose(Axis.XP.rotationDegrees(-90));
            stack.mulPose(Axis.YP.rotationDegrees(-135));
            stack.scale(1.2f, 1.2f, 1.2f);

            LoadersHolder.JSG_HOLDER.texture().getTexture(DestinyFloorChevronRenderer.BASE_TEXTURE).bindTexture();
            ModelsHolder.DESTINY_CHEVRON.render(stack);

            var texture = new ResourceLocation(JSG.MOD_ID, DestinyFloorChevronRenderer.BASE_TEXTURE.getPath().replace(".png", "_light.png"));
            LoadersHolder.JSG_HOLDER.texture().getTexture(texture).bindTexture();
            ModelsHolder.DESTINY_CHEVRON.render(stack, true);
            stack.popPose();
        };
    }
}
