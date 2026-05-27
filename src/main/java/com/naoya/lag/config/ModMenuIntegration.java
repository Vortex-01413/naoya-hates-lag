kpackage com.naoya.lag.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // This will hook your mod's configuration screen factory up seamlessly with Mod Menu
        return parent -> null; 
    }
}
