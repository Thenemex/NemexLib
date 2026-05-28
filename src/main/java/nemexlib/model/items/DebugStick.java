package nemexlib.model.items;

import nemexlib.api.items.data.AItem;
import net.minecraft.creativetab.CreativeTabs;

public class DebugStick extends AItem {

    public DebugStick(String unlocalizedName) {
        super("debug_stick", CreativeTabs.tabTools, 1);
    }
}
