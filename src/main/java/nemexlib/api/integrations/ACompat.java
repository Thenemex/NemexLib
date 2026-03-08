package nemexlib.api.integrations;

import cpw.mods.fml.common.Loader;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;

/**
 * Class used for loading mod integration
 */
public abstract class ACompat implements ICompat {

    protected final String mod, tab;

    /**
     * Constructor for the mod integration class
     * @param mod The modID
     */
    public ACompat(String mod) {
        this(mod, "");
    }
    public ACompat(String mod, String tab) {
        if (mod == null) throw new ParameterIsNullOrEmpty();
        this.mod = mod;
        this.tab = tab;
        loadIntegration();
    }

    /**
     * Tells if the mod related to the mod field is loaded
     * @return True if loaded
     */
    public boolean isModLoaded() {
        return Loader.isModLoaded(mod);
    }
    /**
     * Tells if the mod is loaded, and if its config entry is set to true
     * @param mod The modID
     * @param config The config entry
     * @return True if mod is loaded + config entry is set to true
     */
    public static boolean isModLoaded(String mod, boolean config) {
        return Loader.isModLoaded(mod) && config;
    }
}
