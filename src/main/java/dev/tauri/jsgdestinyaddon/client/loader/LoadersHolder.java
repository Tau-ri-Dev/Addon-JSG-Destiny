package dev.tauri.jsgdestinyaddon.client.loader;

import dev.tauri.jsg.api.loader.model.APIOBJLoader;
import dev.tauri.jsg.api.loader.texture.APITextureLoader;
import dev.tauri.jsgdestinyaddon.JSGDestinyAddon;

public class LoadersHolder {
    public static final APITextureLoader TEXTURE_LOADER = APITextureLoader.createLoader(JSGDestinyAddon.MOD_ID, JSGDestinyAddon.class);
    public static final APIOBJLoader MODEL_LOADER = APIOBJLoader.createLoader(JSGDestinyAddon.MOD_ID, JSGDestinyAddon.class);
}
