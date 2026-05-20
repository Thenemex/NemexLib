package nemexlib;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import nemexlib.config.AConfig;
import nemexlib.config.Config;
import nemexlib.model.commands.CommandFindResearchFromRecipe;
import nemexlib.model.commands.CommandGetHeldItemNBT;
import nemexlib.model.commands.CommandGetResearchParents;
import nemexlib.model.commands.CommandGetResearchTriggers;
import nemexlib.api.util.Logger;

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
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        // ToDo Add a config entry for registering commands
        event.registerServerCommand(new CommandFindResearchFromRecipe());
        event.registerServerCommand(new CommandGetHeldItemNBT());
        event.registerServerCommand(new CommandGetResearchParents());
        event.registerServerCommand(new CommandGetResearchTriggers());
        logger.info("Registered 4 new commands");
    }
}
