package nemexlib.api.recipes.arcane;

import nemexlib.api.recipes.removers.ACollectionRecipeRemover;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.IArcaneRecipe;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class ArcaneRemover extends ACollectionRecipeRemover {

    public ArcaneRemover(Collection ... registries) {
        super(registries);
    }
    public ArcaneRemover(int amount, Collection ... registries) {
        super(amount, registries);
    }

    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item</p>
     */
    @Override
    public void removeItem(ItemStack output) {
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
    public void removeAmount(ItemStack output) {
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
    public void removeMeta(ItemStack output) {
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
    public void removePrecise(ItemStack output) {
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
