package nemexlib.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

@SuppressWarnings({"SameParameterValue", "ResultOfMethodCallIgnored"})
public abstract class AConfig {

    private Configuration config;

    public AConfig(FMLPreInitializationEvent event) {
        this.createFiles(event.getSuggestedConfigurationFile());
    }
    public AConfig(FMLPreInitializationEvent event, String fileName) {
        this.createFiles(event.getModConfigurationDirectory(), fileName);
    }
    public AConfig(FMLPreInitializationEvent event, String folderName, String fileName) {
        File folder = new File(event.getModConfigurationDirectory(), folderName);
        folder.mkdirs();
        this.createFiles(new File(folder, fileName.concat(".cfg")));
    }

    private void createFiles(File configFile) {
        if (configFile == null) throw new ParameterIsNullOrEmpty();
        this.config = new Configuration(configFile);
    }
    private void createFiles(File configFolder, String fileName) {
        if (configFolder == null || fileName == null) throw new ParameterIsNullOrEmpty();
        this.config = new Configuration(new File(configFolder, fileName.concat(".cfg")));
    }

    private void init() {
        config.load();
        loadConfig();
        config.save();
    }

    protected abstract void loadConfig();

    protected boolean newEntry(String category, String key) {
        return newEntry(category, key, true);
    }
    protected boolean newEntry(String category, String key, String comment) {
        return newEntry(category,key, true, comment);
    }
    protected boolean newEntry(String category, String key, boolean enabled) {
        return config.get(category, key, enabled).getBoolean(enabled);
    }
    protected boolean newEntry(String category, String key, boolean enabled, String comment) {
        return config.get(category, key, enabled, comment).getBoolean(enabled);
    }
}
