package github.erb3.fabric.nohotbarlooping;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nohotbarlooping implements ClientModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("NoHotbarLooping");
    public static MinecraftClient client;
    public static boolean enabled = true;
    private static KeyBinding keyBind;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello from NoHotbarLooping main class!");
        client = MinecraftClient.getInstance();

        keyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "nohotbarlooping.keybind.name",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "nohotbarlooping.keybind.category"
        ));

        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            while (keyBind.wasPressed()) {
                enabled = !enabled;
            }
        });
    }

    public static Text translate(String key) {
        return Text.translatable("nohotbarlooping." + key);
    }
}
