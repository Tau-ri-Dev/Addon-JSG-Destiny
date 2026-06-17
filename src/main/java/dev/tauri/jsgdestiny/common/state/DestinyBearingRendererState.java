package dev.tauri.jsgdestiny.common.state;

import dev.tauri.jsg.core.client.renderer.Activation;
import dev.tauri.jsg.core.common.entity.State;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class DestinyBearingRendererState extends State {
    public final List<Activation<Object>> activations = new ArrayList<>();
    public boolean lastActiveState = false;
    public float currentState = 0;
    public boolean active;

    public DestinyBearingRendererState() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(active);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        active = buf.readBoolean();
    }
}
