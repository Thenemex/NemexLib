package nemexlib.api.util.exceptions;

public class RecipeCollectionIsNotSetOrEmpty extends TCRException {

    public RecipeCollectionIsNotSetOrEmpty() {
        super("The collection of recipes is not set ; likely null or empty");
    }
}
