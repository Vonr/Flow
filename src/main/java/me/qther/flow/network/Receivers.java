package me.qther.flow.network;

import me.qther.flow.actions.Dash;
import me.qther.flow.init.Client;
import me.qther.flow.init.Flow;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class Receivers {
    @Environment(EnvType.CLIENT)
    public static void initClient() {
        ClientPlayNetworking
            .registerGlobalReceiver(PacketIdentifiers.LOGIN_MOD_PRESENCE_PACKET_ID, (client, handler, buf, responseSender) -> {
                client.execute(() -> {
                    Client.enabled = true;
                    if (client.getServer() != null) Client.lastIP = client.getServer().getServerIp();
                    Flow.LOGGER.info("Mod is present on \"" + Client.lastIP + "\", enabling features.");
                });
            });
    }

    public static void initServer() {
        ServerPlayNetworking
            .registerGlobalReceiver(PacketIdentifiers.DASH_PACKET_ID, (server, player, handler, buf, responseSender) -> {
                server.execute(() -> {
                    Dash.dash(player);
                });
            });
    }
}
