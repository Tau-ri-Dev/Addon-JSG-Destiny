package dev.tauri.jsgdestiny.client;

import dev.tauri.jsg.core.client.IModelsHolder;
import dev.tauri.jsg.core.client.LoadersHolder;
import dev.tauri.jsg.core.common.entity.BiomeOverlayInstance;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ModelsHolder implements IModelsHolder {
    DESTINY_BEARING_BODY("bearing/bearing_body.obj", "bearing/bearing_body.png", false),
    DESTINY_BEARING_LIGHT("bearing/bearing_light.obj", "bearing/bearing_light_on.png", false),

    DESTINY_CHEVRON("floor_chevron.obj", "", false);

    public final ResourceLocation model;
    public final Map<BiomeOverlayInstance, ResourceLocation> biomeTextureResourceMap = new HashMap<>();
    private final List<BiomeOverlayInstance> nonExistingReported = new ArrayList<>();

    ModelsHolder(String modelPath, String texturePath, boolean byOverlay) {
        this.model = ClientConstants.LOADERS_HOLDER.model().getModelResource(modelPath);
        loadEntry(texturePath, byOverlay);
    }

    @Override
    public @NotNull LoadersHolder getLoadersHolder() {
        return ClientConstants.LOADERS_HOLDER;
    }

    @Override
    public @NotNull ResourceLocation getModelLocation() {
        return model;
    }

    @Override
    public @NotNull Map<BiomeOverlayInstance, ResourceLocation> getBiomeTextureResourceMap() {
        return biomeTextureResourceMap;
    }

    @Override
    public @NotNull List<BiomeOverlayInstance> getNonExistingTexturesReported() {
        return nonExistingReported;
    }
}
