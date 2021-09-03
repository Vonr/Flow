package me.qther.flow.init;

import me.qther.flow.Flow;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl;
import net.minecraft.client.MinecraftClient;

public class Client implements ClientModInitializer {
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static boolean enabled = false;

    @Override
    public void onInitializeClient() {
        ClientSidePacketRegistryImpl.INSTANCE.register(Flow.LOGIN_MOD_PRESENCE_PACKET_ID,
            (packetContext, attachedData) -> packetContext.getTaskQueue().execute(() -> {
                enabled = true;
            }));
    }
}
