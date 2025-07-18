package nemexlib.api.oredict;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Class for registering new Ore Dictionnary entries
 */
public class AOreDict {

    /**
     * Constructor that add Items to an Ore Dictionnary entry (and creates it if it doesn't exist)
     * @param name The Ore Dictionary Entry
     * @param ores The items to add to the entry
     */
    public AOreDict(String name, ItemStack ... ores) {
        OreDictionary.getOres(name, true);
        for (ItemStack ore : ores)
            OreDictionary.registerOre(name, ore);
    }
}
