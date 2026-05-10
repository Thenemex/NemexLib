package nemexlib;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import nemexlib.api.commands.CommandFindResearchFromRecipe;
import nemexlib.api.commands.CommandGetHeldItemNBT;
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
        event.registerServerCommand(new CommandGetHeldItemNBT());
        event.registerServerCommand(new CommandFindResearchFromRecipe());
    }
}
