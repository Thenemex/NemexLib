package nemexlib.api.util.exceptions;

public class ParameterArraySizeException extends TCRException {

    public ParameterArraySizeException(int size, int supposedSize) {
        super("Parameter array is " + size + "but should be " + supposedSize);
    }

    public ParameterArraySizeException(int maxSize) {
        super("Parameter array max size is " + maxSize);
    }
}
