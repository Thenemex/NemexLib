package nemexlib.api.recipes.infusion;

import nemexlib.api.recipes.removers.ACollectionRecipeRemover;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.InfusionRecipe;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class InfusionRemover extends ACollectionRecipeRemover {

    public InfusionRemover(Collection ... registries) {
        super(registries);
    }
    public InfusionRemover(int amount, Collection ... registries) {
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
                    InfusionRecipe r = (InfusionRecipe) recipe;
                    boolean condition = ((ItemStack) r.getRecipeOutput()).getItem().equals(output.getItem());
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
                    InfusionRecipe r = (InfusionRecipe) recipe;
                    boolean condition = ((ItemStack) (r.getRecipeOutput())).getItem().equals(output.getItem())
                            && ((ItemStack) (r.getRecipeOutput())).stackSize == output.stackSize;
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
                    InfusionRecipe r = (InfusionRecipe) recipe;
                    boolean condition = ((ItemStack) (r.getRecipeOutput())).getItem().equals(output.getItem())
                            && ((ItemStack) (r.getRecipeOutput())).getItemDamage() == output.getItemDamage();
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
                    InfusionRecipe r = (InfusionRecipe) recipe;
                    boolean condition = ((ItemStack) (r.getRecipeOutput())).getItem().equals(output.getItem())
                            && ((ItemStack) (r.getRecipeOutput())).stackSize == output.stackSize
                            && ((ItemStack) (r.getRecipeOutput())).getItemDamage() == output.getItemDamage();
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
}
