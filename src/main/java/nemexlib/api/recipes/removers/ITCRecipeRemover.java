package nemexlib.api.recipes.removers;

import net.minecraft.item.ItemStack;

public interface ITCRecipeRemover {

    void removeItem(final ItemStack item, String key);
    void removeAmount(final ItemStack item, String key);
    void removeMeta(final ItemStack item, String key);
    void removePrecise(final ItemStack item, String key);
}
