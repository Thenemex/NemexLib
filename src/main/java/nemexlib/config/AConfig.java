package nemexlib.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Class to extends for making your own config file
 * <p>First use the constructor you want, depending on your coding style</p>
 * <p>Next implement the method <code>loadConfig()</code>. It will be called in <code>init()</code></p>
 * <p>Finally you can create an instance of your config class in <code>preInit()</code>, attach it in a static field and chain <code>init()</code> to it.</p>
 */
@SuppressWarnings({"SameParameterValue", "ResultOfMethodCallIgnored"})
public abstract class AConfig {

    protected Configuration config;

    /**
     * Default constructor, setting the config file in the default config folder, with the modID as filename
     * @param event The event from <code>preInit()</code>
     */
    public AConfig(FMLPreInitializationEvent event) {
        if (event == null) throw new ParameterIsNullOrEmpty();
        this.createFiles(event.getSuggestedConfigurationFile());
    }
    /**
     * Default constructor with version, setting the config file in the default config folder, with the modID as filename
     * <p>Adds a parameter for the config version</p>
     * @param event The event from <code>preInit()</code>
     * @param version The config version
     */
    public AConfig(FMLPreInitializationEvent event, String version) {
        if (event == null || version == null) throw new ParameterIsNullOrEmpty();
        this.createFiles(event.getSuggestedConfigurationFile(), version);
    }
    /**
     * Constructor with folder name and file name. Will create a folder inside the default config folder, and place the config file inside of it.
     * @param event The event from <code>preInit()</code>
     * @param folderName The name of the folder - have to respect usual folder naming rules (no checks will be performed)
     * @param fileName The name of the config file (don't add ".cfg" extension, it will be added by the code)
     */
    public AConfig(FMLPreInitializationEvent event, String folderName, String fileName) {
        if (event == null || folderName == null || fileName == null) throw new ParameterIsNullOrEmpty();
        File folder = new File(event.getModConfigurationDirectory(), folderName);
        folder.mkdirs();
        this.createFiles(new File(folder, fileName.concat(".cfg")));
    }
    /**
     * Constructor with folder name, file name and config version. Will create a folder inside the default config folder, and place the config file inside of it.
     * <p>Adds a parameter for the config version</p>
     * @param event The event from <code>preInit()</code>
     * @param folderName The name of the folder - have to respect usual folder naming rules (no checks will be performed)
     * @param fileName The name of the config file (don't add ".cfg" extension, it will be added by the code)
     * @param version The config version
     */
    public AConfig(FMLPreInitializationEvent event, String folderName, String fileName, String version) {
        if (event == null || folderName == null || fileName == null || version == null) throw new ParameterIsNullOrEmpty();
        File folder = new File(event.getModConfigurationDirectory(), folderName);
        folder.mkdirs();
        this.createFiles(new File(folder, fileName.concat(".cfg")), version);
    }

    /**
     * Constructor with file only. That file will be directly given to the Configuration constructor without checking.
     * <p>Be sure to check the ".cfg" for the file name, and everything else concerning intermediate folders</p>
     * @param configFile The config file instance
     */
    public AConfig(File configFile) {
        if (configFile == null) throw new ParameterIsNullOrEmpty();
        this.createFiles(configFile);
    }
    /**
     * Constructor with file only and version. That file will be directly given to the Configuration constructor without checking.
     * <p>Be sure to check the ".cfg" for the file name, and everything else concerning intermediate folders</p>
     * <p>Adds a parameter for the config version</p>
     * @param configFile The config file instance
     * @param version The config version
     */
    public AConfig(File configFile, String version) {
        if (configFile == null || version == null) throw new ParameterIsNullOrEmpty();
        this.createFiles(configFile, version);
    }

    /**
     * Private method used by the constructors to initialize the Configuration file
     */
    private void createFiles(File configFile) {
        this.config = new Configuration(configFile);
    }
    /**
     * Private method used by the constructors to initialize the Configuration file
     */
    private void createFiles(File configFile, String version) {
        this.config = new Configuration(configFile, version);
    }

    /**
     * Method to call once the config instance is constructed
     * <p>Remember to implement the method <code>loadConfig()</code> before calling this one</p>
     * @return Itself
     */
    public AConfig init() {
        config.load();
        loadConfig();
        config.save();
        return this;
    }

    /**
     * Method to implement to load your own config entries, or anything you want
     */
    protected abstract void loadConfig();

    /**
     * Create a new boolean entry to the config
     * @param category The category
     * @param key The entry key
     * @return The value of the entry
     */
    protected boolean newEntry(String category, String key) {
        return newEntry(category, key, true);
    }
    /**
     * Create a new boolean entry to the config
     * @param category The category
     * @param key The entry key
     * @param comment A comment that will be shown on the line above
     * @return The value of the entry
     */
    protected boolean newEntry(String category, String key, String comment) {
        return newEntry(category,key, true, comment);
    }
    /**
     * Create a new boolean entry to the config
     * @param category The category
     * @param key The entry key
     * @param enabled The default value of the entry
     * @return The value of the entry
     */
    protected boolean newEntry(String category, String key, boolean enabled) {
        return config.get(category, key, enabled).getBoolean(enabled);
    }
    /**
     * Create a new boolean entry to the config
     * @param category The category
     * @param key The entry key
     * @param comment A comment that will be shown on the line above
     * @param enabled The default value of the entry
     * @return The value of the entry
     */
    protected boolean newEntry(String category, String key, boolean enabled, String comment) {
        return config.get(category, key, enabled, comment).getBoolean(enabled);
    }

    /**
     * Create a new int entry to the config
     * @param category The category
     * @param key The entry key
     * @param value The default value
     * @return The value of the entry
     */
    protected int newEntry(String category, String key, int value) {
        return config.get(category, key, value).getInt(value);
    }
    /**
     * Create a new int entry to the config
     * @param category The category
     * @param key The entry key
     * @param value The default value
     * @param comment A comment that will be shown on the line above
     * @return The value of the entry
     */
    protected int newEntry(String category, String key, int value, String comment) {
        return config.get(category, key, value, comment).getInt(value);
    }
    /**
     * Create a new int entry to the config
     * @param category The category
     * @param key The entry key
     * @param value The default value
     * @param min Minimum value
     * @param max Maximum value
     * @param comment A comment that will be shown on the line above
     * @return The value of the entry
     */
    protected int newEntry(String category, String key, int value, int min, int max, String comment) {
        return config.get(category, key, value, comment, min, max).getInt(value);
    }
}
