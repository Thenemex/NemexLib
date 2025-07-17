package tcreborn;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import static tcreborn.NemexLib.modID;
import static tcreborn.NemexLib.dependencies;

@SuppressWarnings("unused")
@Mod(modid = modID, useMetadata = true, version = "1.0", dependencies = dependencies)
public class NemexLib {

    public static final String modID = "NemexLib";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ignoredEvent) {}

    @Mod.EventHandler
    public void init(FMLInitializationEvent ignoredEvent) {}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent ignoredEvent) {}

    public static final String dependencies = "required-after:Thaumcraft@[4.2.3.5,)";
}
