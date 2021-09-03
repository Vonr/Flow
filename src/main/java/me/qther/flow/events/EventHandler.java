package me.qther.flow.events;

import io.netty.buffer.Unpooled;
import me.qther.flow.Capabilities;
import me.qther.flow.Flow;
import me.qther.flow.binds.KeyHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.server.world.ServerWorld;

public class EventHandler {
    public static void registerWorld(ServerWorld world) {
        Capabilities.handleInvincibility();
        Capabilities.handleDashCooldown();
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient(MinecraftClient client) {
//        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER && !Client.enabled) return;
        KeyHandler.handleKeys();
    }

    public static void onJoin(PacketListener listener, PacketSender packetSender, Object ignored) {
        PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
        packetSender.sendPacket(Flow.LOGIN_MOD_PRESENCE_PACKET_ID, passedData);
    }
}
