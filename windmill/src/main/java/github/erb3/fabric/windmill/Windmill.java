package github.erb3.fabric.windmill;

import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Windmill implements ModInitializer {

    public static boolean enabled = true;
    public static Logger LOGGER = LoggerFactory.getLogger("windmill");
    public static Config conf = new Config();
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        conf.loadConfig();
    }

    public static Text translate(String key) {
        return Text.translatable("windmill" + "." + key);
    }
}
