package nemexlib.api.util.exceptions;

public class EssentiaAmountIsWrong extends TCRException {

    public EssentiaAmountIsWrong(final int min, final int max, final int value) {
        super(value + " is wrong and have to be between " + min + " and " + max + ".");
    }
}
