package github.erb3.fabric.windmill;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class Config {
    private static final File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "windmill.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void saveConfig() {
        try (FileWriter writer = new FileWriter(configFile)) {
            gson.toJson(Windmill.enabled, writer);
        } catch (IOException e) {
            Windmill.LOGGER.error("Could not save Windmill config!");
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try (Reader reader = new FileReader(configFile)) {
            Windmill.enabled = gson.fromJson(reader, Boolean.class);
        } catch (Exception e) {
            if (configFile.exists()) {
                Windmill.LOGGER.error("Config file exists, but could not load.");
                e.printStackTrace();
            } else {
                Windmill.LOGGER.warn("Config file does not exist!");
            }
        }
    }

    public void toggle() {
        Windmill.enabled = !Windmill.enabled;
        this.saveConfig();
    }
}
