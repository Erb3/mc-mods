package github.erb3.fabric.nohotbarlooping;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nohotbarlooping implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("NoHotbarLooping");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from NoHotbarLooping main class!");
    }
}
