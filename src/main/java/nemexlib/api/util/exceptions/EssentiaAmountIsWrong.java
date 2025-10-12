package nemexlib.api.util.exceptions;

public class EssentiaAmountIsWrong extends TCRException {

    public EssentiaAmountIsWrong(int min, int max, int value) {
        super(value + " is wrong and have to be between " + min + " and " + max + ".");
    }
}
