package nemexlib.api.recipes.removers;

import net.minecraft.item.ItemStack;

public interface ITCRecipeRemover {

    void removeItem(ItemStack item, String key);
    void removeAmount(ItemStack item, String key);
    void removeMeta(ItemStack item, String key);
    void removePrecise(ItemStack item, String key);
}
