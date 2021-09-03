package me.qther.flow.binds;

import me.qther.flow.Capabilities;
import me.qther.flow.actions.Dash;
import me.qther.flow.init.Client;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.KeyBinding;

@Environment(EnvType.CLIENT)
public class KeyHandler {
    public static void handleKeys() {
        for (KeyBinding key : Keys.KEYS) {
            while (key.wasPressed()) {
                if (Keys.DASH.equals(key) && !Capabilities.DASH_COOLDOWN.containsKey(Client.MC.player)) {
                    Dash.dash(Client.MC.player);
                }
            }
        }
    }
}
