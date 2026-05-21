package nemexlib.model.config;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import nemexlib.NemexLib;
import nemexlib.model.commands.CommandFindResearchFromRecipe;
import nemexlib.model.commands.CommandGetHeldItemNBT;
import nemexlib.model.commands.CommandGetResearchParents;
import nemexlib.model.commands.CommandGetResearchTriggers;

import static nemexlib.config.Config.*;

public class ConfigCommands {

    public static void init(FMLServerStartingEvent event) {
        if (findResearchFromRecipe) event.registerServerCommand(new CommandFindResearchFromRecipe());
        if (getHeldItemNBT) event.registerServerCommand(new CommandGetHeldItemNBT());
        if (getResearchParents) event.registerServerCommand(new CommandGetResearchParents());
        if (getResearchTriggers) event.registerServerCommand(new CommandGetResearchTriggers());
        NemexLib.logger.info("Registered 4 commands");
    }
}
