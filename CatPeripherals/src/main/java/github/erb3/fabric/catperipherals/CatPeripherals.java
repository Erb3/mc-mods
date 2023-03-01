package github.erb3.fabric.catperipherals;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatPeripherals implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("CatPeripherals");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from CatPeripherals!");
        PeripheralManager.registerBlocks();
    }
}
