package dev.tauri.jsgdestiny.common.packet;

import dev.tauri.jsg.api.packet.SimplePacketHandler;
import dev.tauri.jsgdestiny.JSGDestiny;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class JSGDPacketHandler {
    private static final SimplePacketHandler HANDLER = new SimplePacketHandler(new ResourceLocation(JSGDestiny.MOD_ID, "main"), "1.0");

    public static void sendToServer(Object packet) {
        HANDLER.sendToServer(packet);
    }

    public static void sendToClient(Object packet, PacketDistributor.TargetPoint point) {
        HANDLER.sendToClient(packet, point);
    }

    public static void sendTo(Object packet, ServerPlayer player) {
        HANDLER.sendTo(packet, player);
    }

    public static void init() {
    }
}
