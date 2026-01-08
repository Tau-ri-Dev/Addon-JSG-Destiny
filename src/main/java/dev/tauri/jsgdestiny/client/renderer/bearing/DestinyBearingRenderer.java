package dev.tauri.jsgdestiny.client.renderer.bearing;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.tauri.jsg.api.blockstates.JSGProperties;
import dev.tauri.jsg.api.config.JSGConfig;
import dev.tauri.jsg.helpers.BlockPosHelper;
import dev.tauri.jsg.renderer.activation.Activation;
import dev.tauri.jsgdestiny.client.ClientConstants;
import dev.tauri.jsgdestiny.client.ModelsHolder;
import dev.tauri.jsgdestiny.client.renderer.activation.BearingActivation;
import dev.tauri.jsgdestiny.common.blockentity.DestinyBearingBE;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;

import javax.annotation.ParametersAreNonnullByDefault;

public class DestinyBearingRenderer implements BlockEntityRenderer<DestinyBearingBE> {
    public DestinyBearingRenderer(BlockEntityRendererProvider.Context ignored) {
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean shouldRenderOffScreen(DestinyBearingBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 64 * 3;
    }

    public static final String TEXTURE_ON = "bearing/bearing_light_on.png";
    public static final String TEXTURE_OFF = "bearing/bearing_light_off.png";

    public int getCombinedLight(DestinyBearingBE blockEntity) {
        if (blockEntity.getLevel() == null) return 0;
        int count = 0;
        int count2 = 0;
        long blockSum = 0;
        long skySum = 0;
        for (var side : Direction.values()) {
            count++;
            count2 += 2;
            int light = LevelRenderer.getLightColor(blockEntity.getLevel(), blockEntity.getBlockPos().offset(side.getNormal()));
            blockSum += (LightTexture.block(light) * 2L);
            skySum += LightTexture.sky(light);
        }
        if (count == 0) return LightTexture.FULL_BRIGHT;
        return LightTexture.pack((int) (blockSum / count2), (int) (skySum / count));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(DestinyBearingBE blockEntity, float partTicks, PoseStack stack, MultiBufferSource pBuffer, int light, int overlay) {
        if (blockEntity.getLevel() == null) return;
        var rs = blockEntity.rendererState;
        light = getCombinedLight(blockEntity);
        blockEntity.getLevel().updateSkyBrightness();

        stack.pushPose();
        RenderSystem.enableBlend();
        stack.translate(0.5, 0.5, 0.5);
        stack.mulPose(Axis.YP.rotationDegrees(getRotation(blockEntity)));
        var y = DestinyBearingHeightAdjust.getY(blockEntity);
        switch (JSGConfig.Stargate.stargateSize.get()) {
            case SMALL:
                stack.translate(0, y, 0);
                stack.scale(3.2f, 3.2f, 3.2f);
                break;
            case LARGE:
                stack.translate(0, y, 0);
                stack.scale(5.2f, 5.2f, 5.2f);
                break;
            default:
                stack.translate(0, y, 0);
                stack.scale(4.2f, 4.2f, 4.2f);
                break;
        }

        RenderSystem.clearColor(1, 1, 1, 1);
        ModelsHolder.DESTINY_BEARING_BODY.bindTexture().render(stack, pBuffer, light, overlay);

        var active = rs.active;
        if (rs.lastActiveState != active) {
            rs.lastActiveState = active;
            rs.activations.add(new BearingActivation(null, blockEntity.getLevel().getGameTime(), !active));
        }
        Activation.iterate(rs.activations, blockEntity.getLevel().getGameTime(), partTicks, (ignored, state) -> rs.currentState = state);

        ClientConstants.LOADERS_HOLDER.texture().getTexture(ClientConstants.LOADERS_HOLDER.texture().getTextureResource(rs.currentState >= 0.3f ? TEXTURE_ON : TEXTURE_OFF)).bindTexture();
        ModelsHolder.DESTINY_BEARING_LIGHT.render(stack, pBuffer, light, rs.currentState > 0, rs.currentState > 0 ? rs.currentState + 0.3f : 1f);
        stack.popPose();
    }

    protected float getRotation(DestinyBearingBE blockEntity) {
        if (blockEntity.getLevel() == null) return 0;
        return blockEntity.getLevel().getBlockState(blockEntity.getBlockPos()).getOptionalValue(JSGProperties.FACING_HORIZONTAL_PROPERTY).map(v -> BlockPosHelper.getIntRotation(v, false)).orElse(0);
    }
}
