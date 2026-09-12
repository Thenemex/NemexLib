package nemexlib.api.recipes.removers;

import net.minecraft.item.ItemStack;

public interface IRecipeRemover {

    void removeItem(final ItemStack item);
    void removeAmount(final ItemStack item);
    void removeMeta(final ItemStack item);
    void removePrecise(final ItemStack item);
}
