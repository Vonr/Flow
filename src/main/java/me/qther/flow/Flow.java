package me.qther.flow;

import me.qther.flow.events.EventHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Flow implements ModInitializer {
	public static final String ID = "flow";
	public static final Logger LOGGER = LogManager.getLogger(ID);

	public static Identifier id(String name) {
		return new Identifier(ID, name);
	}
	public static final Identifier LOGIN_MOD_PRESENCE_PACKET_ID = id("presence");

	@Override
	public void onInitialize() {
		ServerTickEvents.END_WORLD_TICK.register(EventHandler::registerWorld);
		ClientTickEvents.END_CLIENT_TICK.register(EventHandler::registerClient);
		ServerPlayConnectionEvents.JOIN.register(EventHandler::onJoin);
		ClientPlayConnectionEvents.JOIN.register(EventHandler::onJoin);

		LOGGER.info("Flow initialized.");
	}
}
