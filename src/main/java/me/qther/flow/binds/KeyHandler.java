package me.qther.flow.binds;

import me.qther.flow.events.Capabilities;
import me.qther.flow.actions.Dash;
import me.qther.flow.init.Client;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.KeyBinding;

@Environment(EnvType.CLIENT)
public class KeyHandler {
    public static void handleKeys() {
        if (!Client.enabled) return;
        for (KeyBinding key : Client.KEYS.KEYS) {
            while (key.wasPressed()) {
                if (Client.KEYS.DASH.equals(key) && !Capabilities.DASH_COOLDOWN.containsKey(Client.MC.player)) {
                    Dash.sendDash(Client.MC.player);
                }
            }
        }
    }
}
