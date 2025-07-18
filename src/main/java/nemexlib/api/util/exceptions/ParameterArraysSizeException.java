package nemexlib.api.util.exceptions;

public class ParameterArraysSizeException extends TCRException {

    public ParameterArraysSizeException(int size1, int size2) {
        this(size1, size2, "Parameters Arrays should be the same size : ");
    }

    protected ParameterArraysSizeException(int size1, int size2, String text) {
        super(text + size1 + " != " + size2);
    }
}
