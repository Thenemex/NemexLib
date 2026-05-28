package nemexlib;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import nemexlib.config.AConfig;
import nemexlib.config.Config;
import nemexlib.api.util.Logger;
import nemexlib.model.config.ConfigCommands;
import nemexlib.model.config.ConfigItems;

import static nemexlib.NemexLib.modID;
import static nemexlib.NemexLib.dependencies;

@SuppressWarnings("unused")
@Mod(modid = modID, useMetadata = true, version = "1.10", dependencies = dependencies)
public class NemexLib {

    public static final String modID = "NemexLib", dependencies = "required-after:Thaumcraft@[4.2.3.5,)";

    public static final Logger logger = new Logger(modID);
    public static AConfig config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Config(event).init();
        if (Config.debugItemsEnabled) ConfigItems.init();
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        if (Config.commandsEnabled) ConfigCommands.init(event);
    }
}
