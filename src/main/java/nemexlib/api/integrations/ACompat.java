package nemexlib.api.integrations;

import cpw.mods.fml.common.Loader;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;

import java.util.Objects;

/**
 * Class used for loading mod integration
 */
public abstract class ACompat {

    protected final String mod;

    /**
     * Constructor for the mod integration class
     * @param mod The modID
     */
    public ACompat(String mod) {
        if (mod == null) throw new ParameterIsNullOrEmpty();
        this.mod = mod;
        loadIntegration();
    }

    /**
     * Method called in the constructor, needs to be implemented with all code for integration
     */
    public abstract void loadIntegration();

    @Override public String toString() {
        return "Compat:".concat(mod);
    }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ACompat)) return false;
        ACompat aCompat = (ACompat) o;
        return Objects.equals(mod, aCompat.mod);
    }
    @Override public int hashCode() {
        return Objects.hashCode(mod);
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
