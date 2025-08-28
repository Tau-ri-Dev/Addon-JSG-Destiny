package dev.tauri.jsgdestiny.client;

import dev.tauri.jsg.loader.IModelsHolder;
import dev.tauri.jsg.loader.LoadersHolder;
import dev.tauri.jsg.stargate.BiomeOverlayRegistry;
import dev.tauri.jsgdestiny.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ModelsHolder implements IModelsHolder {
    DESTINY_BEARING_BODY("bearing/bearing_body.obj", "bearing/bearing_body.png", false),
    DESTINY_BEARING_LIGHT("bearing/bearing_light.obj", "bearing/bearing_light_on.png", false)
    ;

    public final ResourceLocation model;
    public final Map<BiomeOverlayRegistry.BiomeOverlayInstance, ResourceLocation> biomeTextureResourceMap = new HashMap<>();
    private final List<BiomeOverlayRegistry.BiomeOverlayInstance> nonExistingReported = new ArrayList<>();

    ModelsHolder(String modelPath, String texturePath, boolean byOverlay) {
        this.model = Constants.LOADERS_HOLDER.model().getModelResource(modelPath);
        loadEntry(texturePath, byOverlay);
    }

    @Override
    public @NotNull LoadersHolder getLoadersHolder() {
        return Constants.LOADERS_HOLDER;
    }

    @Override
    public @NotNull ResourceLocation getModelLocation() {
        return model;
    }

    @Override
    public @NotNull Map<BiomeOverlayRegistry.BiomeOverlayInstance, ResourceLocation> getBiomeTextureResourceMap() {
        return biomeTextureResourceMap;
    }

    @Override
    public @NotNull List<BiomeOverlayRegistry.BiomeOverlayInstance> getNonExistingTexturesReported() {
        return nonExistingReported;
    }
}
