package github.erb3.fabric.beeperipherals;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeePeripherals implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("BeePeripherals");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from BeePeripherals");
        IntegrationRegister.registerPeripherals();
    }
}
