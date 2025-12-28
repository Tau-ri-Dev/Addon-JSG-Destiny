package dev.tauri.jsgdestiny.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.tauri.jsg.JSG;
import dev.tauri.jsg.api.blockstates.JSGProperties;
import dev.tauri.jsg.api.client.LoadersHolder;
import dev.tauri.jsg.api.registry.BiomeOverlayRegistry;
import dev.tauri.jsg.helpers.BlockPosHelper;
import dev.tauri.jsg.loader.ElementEnum;
import dev.tauri.jsg.renderer.activation.Activation;
import dev.tauri.jsgdestiny.client.ModelsHolder;
import dev.tauri.jsgdestiny.client.renderer.activation.BearingActivation;
import dev.tauri.jsgdestiny.common.blockentity.DestinyBearingBE;
import dev.tauri.jsgdestiny.common.blockentity.DestinyFloorChevronBE;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

public class DestinyFloorChevronRenderer implements BlockEntityRenderer<DestinyFloorChevronBE> {
    public DestinyFloorChevronRenderer(BlockEntityRendererProvider.Context ignored) {
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean shouldRenderOffScreen(DestinyFloorChevronBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 64 * 3;
    }

    public static final ResourceLocation BASE_TEXTURE = ElementEnum.UNIVERSE_CHEVRON.biomeTextureResourceMap.get(BiomeOverlayRegistry.NORMAL);

    @Override
    @ParametersAreNonnullByDefault
    public void render(DestinyFloorChevronBE blockEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {
        if (blockEntity.getLevel() == null) return;
        var rs = blockEntity.rendererState;
        blockEntity.getLevel().updateSkyBrightness();

        stack.pushPose();
        RenderSystem.enableBlend();
        stack.translate(0.5, 0.008f, 0.5);
        stack.mulPose(Axis.YP.rotationDegrees(getRotation(blockEntity)));

        RenderSystem.clearColor(1, 1, 1, 1);
        LoadersHolder.JSG_HOLDER.texture().getTexture(BASE_TEXTURE).bindTexture();
        ModelsHolder.DESTINY_CHEVRON.render(stack, buffer, light, overlay);

        var active = rs.active;
        if (rs.lastActiveState != active) {
            rs.lastActiveState = active;
            rs.activations.add(new BearingActivation(null, blockEntity.getLevel().getGameTime(), !active));
        }
        Activation.iterate(rs.activations, blockEntity.getLevel().getGameTime(), partialTicks, (ignored, state) -> rs.currentState = state);

        var texture = new ResourceLocation(JSG.MOD_ID, BASE_TEXTURE.getPath().replace(".png", "_light" + (rs.currentState >= 0.3f ? "" : "_off") + ".png"));
        LoadersHolder.JSG_HOLDER.texture().getTexture(texture).bindTexture();
        ModelsHolder.DESTINY_CHEVRON.render(stack, buffer, light, rs.currentState > 0, rs.currentState > 0 ? rs.currentState + 0.3f : 1f);

        stack.popPose();
    }

    protected float getRotation(DestinyBearingBE blockEntity) {
        if (blockEntity.getLevel() == null) return 0;
        return blockEntity.getLevel().getBlockState(blockEntity.getBlockPos()).getOptionalValue(JSGProperties.FACING_HORIZONTAL_PROPERTY).map(v -> BlockPosHelper.getIntRotation(v, false)).orElse(0);
    }
}
