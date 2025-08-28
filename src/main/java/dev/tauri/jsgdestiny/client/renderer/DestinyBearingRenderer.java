package dev.tauri.jsgdestiny.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.tauri.jsg.config.JSGConfig;
import dev.tauri.jsg.helpers.BlockPosHelper;
import dev.tauri.jsg.loader.model.OBJModel;
import dev.tauri.jsg.property.JSGProperties;
import dev.tauri.jsgdestiny.Constants;
import dev.tauri.jsgdestiny.client.ModelsHolder;
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
        light = getCombinedLight(blockEntity);
        blockEntity.getLevel().updateSkyBrightness();
        OBJModel.renderType = OBJModel.EnumOBJRenderMethod.NORMAL;
        OBJModel.source = pBuffer;
        OBJModel.packedLight = light;
        OBJModel.resetRGB();
        OBJModel.resetDynamicLightning();

        stack.pushPose();
        RenderSystem.enableBlend();
        stack.translate(0.5, 0.5, 0.5);
        stack.mulPose(Axis.YP.rotationDegrees(getRotation(blockEntity)));
        switch (JSGConfig.Stargate.stargateSize.get()) {
            case SMALL:
                stack.translate(0, 0.08f, 0);
                stack.scale(3.2f, 3.2f, 3.2f);
                break;
            case LARGE:
                stack.translate(0, 1.2f, 0);
                stack.scale(5.2f, 5.2f, 5.2f);
                break;
            default:
                stack.translate(0, 1.2f, 0);
                stack.scale(4.2f, 4.2f, 4.2f);
                break;
        }

        RenderSystem.clearColor(1, 1, 1, 1);
        ModelsHolder.DESTINY_BEARING_BODY.bindTextureAndRender(stack);

        var active = blockEntity.isActive();
        Constants.LOADERS_HOLDER.texture().getTexture(Constants.LOADERS_HOLDER.texture().getTextureResource(active ? TEXTURE_ON : TEXTURE_OFF)).bindTexture();
        ModelsHolder.DESTINY_BEARING_LIGHT.render(stack, active);
        stack.popPose();
    }

    protected float getRotation(DestinyBearingBE blockEntity) {
        if (blockEntity.getLevel() == null) return 0;
        return blockEntity.getLevel().getBlockState(blockEntity.getBlockPos()).getOptionalValue(JSGProperties.FACING_HORIZONTAL_PROPERTY).map(v -> BlockPosHelper.getIntRotation(v, false)).orElse(0);
    }
}
