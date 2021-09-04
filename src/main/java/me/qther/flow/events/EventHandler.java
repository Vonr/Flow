package me.qther.flow.events;

import me.qther.flow.binds.KeyHandler;
import me.qther.flow.init.Client;
import me.qther.flow.init.Flow;
import me.qther.flow.network.PacketIdentifiers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
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

    public static void onJoin(ServerPlayNetworkHandler handler, PacketSender packetSender, MinecraftServer minecraftServer) {
        ServerPlayNetworking.send(handler.player, PacketIdentifiers.LOGIN_MOD_PRESENCE_PACKET_ID, PacketByteBufs.empty());
        Flow.LOGGER.info("Sending presence packet to new client.");
    }

    public static void onDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        Client.enabled = false;
        Client.lastIP = "";
    }
}
