package nemexlib.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import nemexlib.NemexLib;

public class Config extends AConfig {

    protected static final String version = "1.0";

    public Config(FMLPreInitializationEvent event) {
        super(NemexLib.modID, event, version);
    }

    @Override
    protected void loadConfig() {

    }
}
