package nemexlib.api.util.exceptions;

public class BlockOrItemDoesNotExist extends TCRException {

    public BlockOrItemDoesNotExist(String mod, String itemName, int meta) {
        super("The item/block with identifier \"" + mod + ":" + itemName + ":" + meta + "\" cannot be found");
    }
}
