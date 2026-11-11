package dev.tauri.jsgdestiny.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.tauri.jsgcore.client.renderer.AbstractItemBEWLR;
import dev.tauri.jsgdestiny.client.ModelsHolder;
import dev.tauri.jsgdestiny.common.item.DestinyBearingItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class DestinyBearingBEWLR extends AbstractItemBEWLR {
    @Override
    public void renderItem(ItemStack itemStack, ItemDisplayContext itemDisplayContext, PoseStack stack, MultiBufferSource bufferSource, int light, int overlay, float partialTick) {
        stack.pushPose();
        if (itemDisplayContext == ItemDisplayContext.GUI) {
            stack.translate(0, 0.2, 0.7);
            stack.scale(1.2f, 1.2f, 1.2f);
        } else if (itemDisplayContext.firstPerson()) {
            stack.translate(0, -0.2, 0.7);
            stack.mulPose(Axis.ZP.rotationDegrees(45 * (itemDisplayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ? -1 : 1)));
            stack.scale(1.2f, 1.2f, 1.2f);
        } else if (itemDisplayContext == ItemDisplayContext.GROUND) {
            stack.scale(2, 2, 2);
        } else {
            stack.translate(0, -0.7, 0.7);
            stack.mulPose(Axis.ZP.rotationDegrees(45 * (itemDisplayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND ? -1 : 1)));
            stack.scale(1.5f, 1.5f, 1.5f);
        }
        ModelsHolder.DESTINY_BEARING_BODY.bindTexture().render(stack, bufferSource, light, overlay);
        var color = new Color(itemStack.getItem() instanceof DestinyBearingItem bearing ? bearing.getRGBColor() : 0xffffffff, true);
        var r = color.getRed() / 255f;
        var g = color.getGreen() / 255f;
        var b = color.getBlue() / 255f;
        ModelsHolder.DESTINY_BEARING_LIGHT.bindTexture().render(stack, bufferSource, light, overlay, false, r, g, b, 1f, false);
        stack.popPose();
    }
}
