package dev.tauri.jsgdestiny.client.renderer.activation;

import dev.tauri.jsg.renderer.activation.Activation;

public class BearingActivation extends Activation<Object> {
    public BearingActivation(Object textureKey, long stateChange, boolean dim) {
        super(textureKey, stateChange, dim);
    }

    @Override
    protected float getMaxStage() {
        return 0.7f;
    }

    @Override
    protected float getTickMultiplier() {
        return 0.2f;
    }
}
