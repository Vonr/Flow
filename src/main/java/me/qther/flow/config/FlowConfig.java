package me.qther.flow.config;

import me.qther.flow.init.Flow;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Flow.ID)
public class FlowConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public Dashing dashing = new Dashing();

    public static class Dashing {
        public boolean enabled = true;
        public double multiplier = 0.7;
        public int ticks = 5;
        public int cooldown = 60;
        public boolean invincibility = true;
        public boolean onGround = false;
        public boolean resetFall = true;
        public float exhaustion = 2F;
    }
}
