package github.erb3.fabric.nohotbarlooping;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nohotbarlooping implements ClientModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("NoHotbarLooping");
    public static MinecraftClient client;
    private static KeyBinding keyBind;
    private static ToastManager toaster;

    public static boolean enabled = true;


    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello from NoHotbarLooping main class!");
        client = MinecraftClient.getInstance();
        toaster = client.getToastManager();

        keyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "nohotbarlooping.keybind.name",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "nohotbarlooping.keybind.category"
        ));

        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            while (keyBind.wasPressed()) {
                enabled = !enabled;
                renderToast(enabled);
            }
        });
    }

    private static void renderToast(boolean enabled) {
        Item item;
        String title;

        if (enabled) {
            item = Items.REDSTONE_BLOCK;
            title = "Hotbar looping disabled";
        } else {
            item = Items.EMERALD_BLOCK;
            title = "Hotbar looping enabled";
        }

        Toast toast = new CustomToast(item, title, "- NoHotbarLooping");
        toaster.clear();
        toaster.add(toast);
    }

    public static Text translate(String key) {
        return Text.translatable("nohotbarlooping." + key);
    }
}
