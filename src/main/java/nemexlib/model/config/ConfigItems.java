package nemexlib.model.config;

import nemexlib.api.items.data.IITem;
import nemexlib.model.items.DebugStick;

public class ConfigItems {

    public static IITem debugStick;

    public static void init() {
        debugStick = new DebugStick();
    }
}
