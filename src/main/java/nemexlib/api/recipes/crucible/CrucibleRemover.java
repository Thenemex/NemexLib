package nemexlib.api.recipes.crucible;

import nemexlib.api.recipes.removers.ACollectionRecipeRemover;
import nemexlib.api.recipes.removers.ITCRecipeRemover;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.CrucibleRecipe;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class CrucibleRemover extends ACollectionRecipeRemover implements ITCRecipeRemover {

    public CrucibleRemover(final Collection... registries) {
        super(registries);
    }
    public CrucibleRemover(final int amount, final Collection ... registries) {
        super(amount, registries);
    }

    /**
     * Remove all recipes that have the ItemStack as output.
     * <p>This method checks : Item</p>
     */
    @Override public void removeItem(final ItemStack output) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
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
    @Override public void removeAmount(final ItemStack output) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
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
    @Override public void removeMeta(final ItemStack output) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
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
    @Override public void removePrecise(final ItemStack output) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
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

    @Override public void removeItem(final ItemStack output, final String key) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.key.equalsIgnoreCase(key);
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
    @Override public void removeAmount(final ItemStack output, final String key) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().stackSize == output.stackSize
                            && r.key.equalsIgnoreCase(key);
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
    @Override public void removeMeta(final ItemStack output, final String key) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().getItemDamage() == output.getItemDamage()
                            && r.key.equalsIgnoreCase(key);
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
    @Override public void removePrecise(final ItemStack output, final String key) {
        for (Collection registry : registries) {
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().stackSize == output.stackSize
                            && r.getRecipeOutput().getItemDamage() == output.getItemDamage()
                            && r.key.equalsIgnoreCase(key);
                    if (condition) this.toRemove.add(r);
                } catch (Exception ignored) {}
            for (Object recipe : toRemove)
                registry.remove(recipe);
            toRemove.clear();
        }
    }
}
