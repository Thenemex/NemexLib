package nemexlib.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.NemexLib;

public class Config extends AConfig {

    public Config(FMLPreInitializationEvent event, String version) {
        super(NemexLib.modID, event, version);
    }

    @Override
    protected void loadConfig() {

    }
}
