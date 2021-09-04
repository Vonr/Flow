package me.qther.flow.binds;

import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Keys {
    public final List<KeyBinding> KEYS = new ArrayList<>();
    public final KeyBinding DASH;

    public Keys() {
        DASH = register("dash", GLFW.GLFW_KEY_F, Category.GAME);
    }

    private KeyBinding register(String name, int key, Category category) {
        KeyBinding keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.flow." + category.name + "." + name, InputUtil.Type.KEYSYM, key, "key.flow." + category.name));
        KEYS.add(keybind);
        return keybind;
    }

    public enum Category {
        GAME("game");

        public String name;
        Category(String name) { this.name = name; }
    }
}
