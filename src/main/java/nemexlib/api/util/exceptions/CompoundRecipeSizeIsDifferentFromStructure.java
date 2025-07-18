package nemexlib.api.util.exceptions;

public class CompoundRecipeSizeIsDifferentFromStructure extends ParameterArraysSizeException {

    public CompoundRecipeSizeIsDifferentFromStructure(int recipeSize, int structureSize) {
        super(recipeSize, structureSize, "The Compound recipe size (w*h*l) is different from the Structure size : ");
    }
}
