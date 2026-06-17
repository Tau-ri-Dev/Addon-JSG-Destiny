package dev.tauri.jsgdestiny.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.tauri.jsg.JSG;
import dev.tauri.jsg.api.JSGApi;
import dev.tauri.jsg.core.client.renderer.AbstractItemBEWLR;
import dev.tauri.jsgdestiny.client.ModelsHolder;
import dev.tauri.jsgdestiny.client.renderer.DestinyFloorChevronRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class DestinyFloorChevronBEWLR extends AbstractItemBEWLR {
    @Override
    public void renderItem(ItemStack itemStack, ItemDisplayContext itemDisplayContext, PoseStack stack, MultiBufferSource bufferSource, int light, int overlay, float partialTick) {
        stack.pushPose();
        if (itemDisplayContext == ItemDisplayContext.GUI) {
            stack.translate(0, 0.3, 0.7);
            stack.scale(0.65f, 0.65f, 0.65f);
            stack.mulPose(Axis.XP.rotationDegrees(-90));
            stack.mulPose(Axis.YP.rotationDegrees(-180));
        } else if (itemDisplayContext.firstPerson()) {
            stack.translate(0, -0.2, 0.7);
            stack.mulPose(Axis.ZP.rotationDegrees(45 * (itemDisplayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ? -1 : 1)));
            stack.scale(0.5f, 0.5f, 0.5f);
            stack.mulPose(Axis.XP.rotationDegrees(-90));
            stack.mulPose(Axis.YP.rotationDegrees(-135 * (itemDisplayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ? -1 : 1)));
        } else if (itemDisplayContext == ItemDisplayContext.GROUND) {
            stack.mulPose(Axis.XP.rotationDegrees(-90));
            stack.mulPose(Axis.YP.rotationDegrees(-135));
        } else {
            stack.translate(0, -0.7, 0.7);
            stack.mulPose(Axis.ZP.rotationDegrees(45 * (itemDisplayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND ? -1 : 1)));
            stack.scale(0.5f, 0.5f, 0.5f);
            stack.mulPose(Axis.XP.rotationDegrees(-90));
            stack.mulPose(Axis.YP.rotationDegrees(-135 * (itemDisplayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND ? -1 : 1)));
        }

        JSGApi.JSG_LOADERS_HOLDER.texture().getTexture(DestinyFloorChevronRenderer.BASE_TEXTURE).bindTexture();
        ModelsHolder.DESTINY_CHEVRON.render(stack, bufferSource, light, overlay, false, 1f, true);

        var texture = new ResourceLocation(JSG.MOD_ID, DestinyFloorChevronRenderer.BASE_TEXTURE.getPath().split("\\.")[0] + "_light");
        JSGApi.JSG_LOADERS_HOLDER.texture().getTexture(texture).bindTexture();
        ModelsHolder.DESTINY_CHEVRON.render(stack, bufferSource, light, overlay, true, 1f, true);
        stack.popPose();
    }
}
