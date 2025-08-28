package dev.tauri.jsgdestiny.common.state;

import dev.tauri.jsg.state.State;
import dev.tauri.jsg.state.StateTypeEnum;
import dev.tauri.jsgdestiny.JSGDestiny;
import io.netty.buffer.ByteBuf;
import net.minecraft.resources.ResourceLocation;

public class DestinyBearingRendererState extends State {

    public static final StateTypeEnum STATE = StateTypeEnum.create(new ResourceLocation(JSGDestiny.MOD_ID, "bearing_state"));


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
