package nemexlib.api.recipes.removers;

import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"rawtypes", "UnusedReturnValue"})
public abstract class ACollectionRecipeRemover implements IRecipeRemover {

    protected final ArrayList<Object> toRemove;
    protected Collection[] registries;

    public ACollectionRecipeRemover(Collection ... registries) {
        this(5, registries);
    }
    public ACollectionRecipeRemover(int amount, Collection ... registries) {
        this.toRemove = new ArrayList<>(amount);
        this.setRegistries(registries);
    }

    public ACollectionRecipeRemover setRegistries(Collection ... registries) {
        if (registries == null || registries.length == 0) throw new ParameterIsNullOrEmpty();
        this.registries = registries;
        return this;
    }
}
