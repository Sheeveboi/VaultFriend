package net.altosheeve.vaultfriend;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keymappings {

    public static KeyBinding addField;
    public static KeyBinding removeField;
    public static KeyBinding yModeToggle;
    public static KeyBinding toggleRender;
    public static void registerKeys() {
        addField = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.vaultfriend.addField",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_LEFT_BRACKET,
                        "key.category.VaultFriend"
                )
        );

        removeField = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.vaultfriend.removeField",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_RIGHT_BRACKET,
                        "key.category.VaultFriend"
                )
        );
        yModeToggle = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.vaultfriend.yModeToggle",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_Y,
                        "key.category.VaultFriend"
                )
        );
        toggleRender = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.vaultfriend.toggleRender",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_Y,
                        "key.category.VaultFriend"
                )
        );
    }

}
