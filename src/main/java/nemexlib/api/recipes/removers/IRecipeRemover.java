package nemexlib.api.recipes.removers;

import net.minecraft.item.ItemStack;

public interface IRecipeRemover {

    void removeItem(ItemStack item);
    void removeAmount(ItemStack item);
    void removeMeta(ItemStack item);
    void removePrecise(ItemStack item);
}
