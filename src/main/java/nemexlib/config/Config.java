package nemexlib.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.NemexLib;

public class Config extends AConfig {

    public static boolean commandsEnabled;
    public static boolean findResearchFromRecipe, getHeldItemNBT, getResearchParents, getResearchTriggers;
    protected static final String version = "1.0";

    public Config(FMLPreInitializationEvent event) {
        super(NemexLib.modID, event, version);
    }

    @Override
    protected void loadConfig() {
        String cat = "Categories";
        {
            commandsEnabled = newEntry(cat, "Commands");
        }
        String commandsCat = "Commands";
        {
            findResearchFromRecipe = newEntry(commandsCat, "FindResearchFromRecipe");
            getHeldItemNBT = newEntry(commandsCat, "GetHeldItemNBT");
            getResearchParents = newEntry(commandsCat, "GetResearchParents");
            getResearchTriggers = newEntry(commandsCat, "GetResearchTriggers");
        }
    }
}
