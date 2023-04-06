package github.erb3.fabric.windmill;

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
                    .setTitle(Windmill.translate("config.title"));

            ConfigCategory general = builder.getOrCreateCategory(
                    Windmill.translate("config.category")
            );

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            general.addEntry(entryBuilder.startBooleanToggle(
                            Windmill.translate("config.enabled.name"),
                            Windmill.enabled
                    )
                    .setDefaultValue(true)
                    .setTooltip(Windmill.translate("config.enabled.description"))
                    .setSaveConsumer((newValue) -> Windmill.conf.toggle())
                    .build());

            return builder.build();
        };
    }
}
