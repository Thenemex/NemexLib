package nemexlib.api.recipes.crucible;

import nemexlib.api.recipes.finders.ACollectionRecipeFinder;
import net.minecraft.item.ItemStack;
import thaumcraft.api.crafting.CrucibleRecipe;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("rawtypes")
public class CrucibleFinder extends ACollectionRecipeFinder<CrucibleRecipe> {

    private final ArrayList<String> researches;

    public CrucibleFinder(final Collection ... registries) {
        this(5, registries);
    }
    public CrucibleFinder(final int amount, final Collection ... registries) {
        super(amount, registries);
        this.researches = new ArrayList<>(amount);
    }

    @Override
    public CrucibleRecipe findRecipeItem(final ItemStack output) {
        checkParameters(output);
        for (Collection registry : registries)
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem());
                    if (condition) return r;
                } catch (Exception ignored) {}
        return null;
    }
    @Override
    public CrucibleRecipe findRecipeAmount(final ItemStack output) {
        checkParameters(output);
        for (Collection registry : registries)
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().stackSize == output.stackSize;
                    if (condition) return r;
                } catch (Exception ignored) {}
        return null;
    }
    @Override
    public CrucibleRecipe findRecipeMeta(final ItemStack output) {
        checkParameters(output);
        for (Collection registry : registries)
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                    if (condition) return r;
                } catch (Exception ignored) {}
        return null;
    }
    @Override
    public CrucibleRecipe findRecipePrecise(final ItemStack output) {
        checkParameters(output);
        for (Collection registry : registries)
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().stackSize == output.stackSize
                            && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                    if (condition) return r;
                } catch (Exception ignored) {}
        return null;
    }

    @Override
    public ArrayList<CrucibleRecipe> findRecipesItem(final ItemStack output) {
        checkParameters(output);
        clearFoundRecipes();
        for (Collection registry : registries)
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem());
                    if (condition) addFoundRecipe(r);
                } catch (Exception ignored) {}
        return getFoundRecipes();
    }
    @Override
    public ArrayList<CrucibleRecipe> findRecipesAmount(final ItemStack output) {
        checkParameters(output);
        clearFoundRecipes();
        for (Collection registry : registries)
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().stackSize == output.stackSize;
                    if (condition) addFoundRecipe(r);
                } catch (Exception ignored) {}
        return getFoundRecipes();
    }
    @Override
    public ArrayList<CrucibleRecipe> findRecipesMeta(final ItemStack output) {
        checkParameters(output);
        clearFoundRecipes();
        for (Collection registry : registries)
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                    if (condition) addFoundRecipe(r);
                } catch (Exception ignored) {}
        return getFoundRecipes();
    }
    @Override
    public ArrayList<CrucibleRecipe> findRecipesPrecise(final ItemStack output) {
        checkParameters(output);
        clearFoundRecipes();
        for (Collection registry : registries)
            for (Object recipe : registry)
                try {
                    CrucibleRecipe r = (CrucibleRecipe) recipe;
                    boolean condition = r.getRecipeOutput().getItem().equals(output.getItem())
                            && r.getRecipeOutput().stackSize == output.stackSize
                            && r.getRecipeOutput().getItemDamage() == output.getItemDamage();
                    if (condition) addFoundRecipe(r);
                } catch (Exception ignored) {}
        return getFoundRecipes();
    }
    
    public String[] getResearchesFromLastFoundRecipes() {
        if (getAmountOfFoundRecipes() == 0) return null;
        researches.clear();
        for (CrucibleRecipe r : getFoundRecipes())
            researches.add(r.key);
        return researches.toArray(new String[0]);
    }
}
