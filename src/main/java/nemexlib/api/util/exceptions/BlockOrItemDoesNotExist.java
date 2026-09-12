package nemexlib.api.util.exceptions;

public class BlockOrItemDoesNotExist extends TCRException {

    public BlockOrItemDoesNotExist() {
        super("Oops ... Something unexpected happen, please report to author !");
    }

    public BlockOrItemDoesNotExist(final String mod, final String itemName, final int meta) {
        super("The item/block with identifier \"" + mod + ":" + itemName + ":" + meta + "\" cannot be found");
    }
}
