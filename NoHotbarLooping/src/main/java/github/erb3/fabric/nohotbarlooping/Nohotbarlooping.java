package github.erb3.fabric.nohotbarlooping;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nohotbarlooping implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("NoHotbarLooping");
    public static MinecraftClient client;
    public static boolean enabled = true;

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from NoHotbarLooping main class!");
        client = MinecraftClient.getInstance();
    }
}