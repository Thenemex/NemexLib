package nemexlib.api.util.exceptions;

public class ParameterArraysSizeException extends TCRException {

    public ParameterArraysSizeException(final int size1, final int size2) {
        this(size1, size2, "Parameters Arrays should be the same size : ");
    }

    protected ParameterArraysSizeException(final int size1, final int size2, final String text) {
        super(text + size1 + " != " + size2);
    }
}
