package nemexlib;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import nemexlib.model.commands.CommandFindResearchFromRecipe;
import nemexlib.model.commands.CommandGetHeldItemNBT;
import nemexlib.model.commands.CommandGetResearchTriggers;
import nemexlib.api.util.Logger;

import static nemexlib.NemexLib.modID;
import static nemexlib.NemexLib.dependencies;

@SuppressWarnings("unused")
@Mod(modid = modID, useMetadata = true, version = "1.9", dependencies = dependencies)
public class NemexLib {

    public static final String modID = "NemexLib", dependencies = "required-after:Thaumcraft@[4.2.3.5,)";

    public static final Logger logger = new Logger(modID);

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFindResearchFromRecipe());
        event.registerServerCommand(new CommandGetHeldItemNBT());
        event.registerServerCommand(new CommandGetResearchTriggers());
        logger.info("Registered 3 new commands");
    }
}
