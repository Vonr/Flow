package me.qther.flow.init;

import me.qther.flow.config.FlowConfig;
import me.qther.flow.events.EventHandler;
import me.qther.flow.network.Receivers;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Flow implements ModInitializer {
	public static final String ID = "flow";
	public static final Logger LOGGER = LogManager.getLogger(ID);
	public static FlowConfig CONFIG;

	public static Identifier id(String name) {
		return new Identifier(ID, name);
	}

	@Override
	public void onInitialize() {
		AutoConfig.register(FlowConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(FlowConfig.class).getConfig();

		ServerTickEvents.END_WORLD_TICK.register(EventHandler::registerWorld);
		ServerPlayConnectionEvents.JOIN.register(EventHandler::onJoin);

		Receivers.initServer();

		LOGGER.info("Flow initialized.");
	}
}
