package github.erb3.fabric.nohotbarlooping;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.of("NoHotbarLooping Config"));

            ConfigCategory general = builder.getOrCreateCategory(Text.of("General"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            general.addEntry(entryBuilder.startBooleanToggle(Text.of("Enabled"), Nohotbarlooping.enabled)
                    .setDefaultValue(true)
                    .setTooltip(Text.of("If NoHotbarLooping should be enabled."))
                    .setSaveConsumer((newValue) -> {
                        Nohotbarlooping.enabled = newValue;
                    })
                    .build());

            return builder.build();
        };
    }
}
