package me.qther.flow.events;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;

public class Capabilities {
    public static Map<Entity, Integer> DASHING = new ConcurrentHashMap<>();
    public static Map<Entity, Integer> DASH_COOLDOWN = new ConcurrentHashMap<>();

    public static void handleInvincibility() {
        DASHING.forEach((entity, time) -> {
            DASHING.put(entity, --time);
            if (--time <= 0) {
                DASHING.remove(entity);
//                entity.setInvulnerable(false);
                entity.setNoGravity(false);
            }
        });
    }

    public static void handleDashCooldown() {
        DASH_COOLDOWN.forEach((entity, time) -> {
            DASH_COOLDOWN.put(entity, --time);
            if (--time <= 0) {
                DASH_COOLDOWN.remove(entity);
            }
        });
    }

}
