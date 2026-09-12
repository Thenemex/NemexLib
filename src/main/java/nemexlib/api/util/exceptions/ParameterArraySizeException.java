package nemexlib.api.util.exceptions;

public class ParameterArraySizeException extends TCRException {

    public ParameterArraySizeException(final int size, final int supposedSize) {
        super("Parameter array is " + size + "but should be " + supposedSize);
    }

    public ParameterArraySizeException(final int maxSize) {
        super("Parameter array max size is " + maxSize);
    }
}
