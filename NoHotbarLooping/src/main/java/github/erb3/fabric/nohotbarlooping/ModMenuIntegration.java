package github.erb3.fabric.nohotbarlooping;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Nohotbarlooping.translate("config.title"));

            ConfigCategory general = builder.getOrCreateCategory(
                    Nohotbarlooping.translate("config.category")
            );

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            general.addEntry(entryBuilder.startBooleanToggle(
                        Nohotbarlooping.translate("config.enabled.name"),
                        Nohotbarlooping.enabled
                    )
                    .setDefaultValue(true)
                    .setTooltip(Nohotbarlooping.translate("config.enabled.description"))
                    .setSaveConsumer((newValue) -> Nohotbarlooping.enabled = newValue)
                    .build());

            return builder.build();
        };
    }
}
