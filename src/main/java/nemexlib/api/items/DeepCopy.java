package nemexlib.api.items;

import net.minecraft.item.ItemStack;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import nemexlib.api.util.exceptions.ParameterValueIsNegativeOrZero;

/**
 * Class used for making deep copy for some objects from Minecraft/Forge code
 */
@SuppressWarnings("unused")
public class DeepCopy {

    /**
     * Make a deep copy of the given ItemStack
     * @param item The Itemstack to deep-copy
     * @return The deep-copy made
     */
    public static ItemStack i(ItemStack item) {
        if (item == null) throw new ParameterIsNullOrEmpty();
        return new ItemStack(item.getItem(), item.stackSize, item.getItemDamage());
    }
    /**
     * Make a deep copy of the given ItemStack with editable stacksize
     * @param item The Itemstack to deep-copy
     * @param nb The new stacksize
     * @return The deep-copy made
     */
    public static ItemStack i(ItemStack item, int nb) {
        if (item == null) throw new ParameterIsNullOrEmpty();
        if (nb < 1) throw new ParameterValueIsNegativeOrZero(nb);
        return new ItemStack(item.getItem(), nb, item.getItemDamage());
    }
    /**
     * Make a deep copy of the given ItemStack array
     * @param items The ItemStack Array to deep-copy
     * @param nb The new stacksize
     * @return The deep-copy made
     */
    public static ItemStack[] i(ItemStack[] items, int nb) {
        if (items.length == 0) throw new ParameterIsNullOrEmpty();
        ItemStack[] deepCopy = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++)
            deepCopy[i] = i(items[i], nb);
        return deepCopy;
    }
}
