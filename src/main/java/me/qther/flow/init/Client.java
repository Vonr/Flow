package me.qther.flow.init;

import me.qther.flow.binds.Keys;
import me.qther.flow.events.EventHandler;
import me.qther.flow.network.Receivers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;

public class Client implements ClientModInitializer {
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static final Keys KEYS = new Keys();
    public static boolean enabled = false;
    public static String lastIP = "";

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(EventHandler::registerClient);
        ClientPlayConnectionEvents.DISCONNECT.register(EventHandler::onDisconnect);

        Receivers.initClient();
    }
}
