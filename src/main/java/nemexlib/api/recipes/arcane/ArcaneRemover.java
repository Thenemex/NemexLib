package nemexlib.api.recipes.arcane;

import nemexlib.api.recipes.removers.ACollectionRecipeRemover;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.IArcaneRecipe;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class ArcaneRemover extends ACollectionRecipeRemover {

    public ArcaneRemover(final Collection ... registries) {
        super(registries);
    }
    public ArcaneRemover(final int amount, final Collection ... registries) {
        super(amount, registries);
    }

    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item</p>
     */
    @Override
    public void removeItem(final ItemStack output) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    IArcaneRecipe r = (IArcaneRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem());
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item and Amount</p>
     */
    @Override
    public void removeAmount(final ItemStack output) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    IArcaneRecipe r = (IArcaneRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().stackSize == output.stackSize;
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item and metadata.</p>
     */
    @Override
    public void removeMeta(final ItemStack output) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    IArcaneRecipe r = (IArcaneRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item, metadata, amount.</p>
     */
    @Override
    public void removePrecise(final ItemStack output) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    IArcaneRecipe r = (IArcaneRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().stackSize == output.stackSize
                            && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
}
