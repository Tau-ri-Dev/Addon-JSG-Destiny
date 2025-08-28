package dev.tauri.jsgdestiny.common.state;

import dev.tauri.jsg.renderer.activation.Activation;
import dev.tauri.jsg.state.State;
import dev.tauri.jsg.state.StateTypeEnum;
import dev.tauri.jsgdestiny.JSGDestiny;
import io.netty.buffer.ByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class DestinyBearingRendererState extends State {

    public static final StateTypeEnum STATE_TYPE = StateTypeEnum.create(new ResourceLocation(JSGDestiny.MOD_ID, "bearing_state"));

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
