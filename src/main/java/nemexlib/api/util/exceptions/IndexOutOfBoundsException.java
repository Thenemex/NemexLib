package nemexlib.api.util.exceptions;

public class IndexOutOfBoundsException extends TCRException {

    public IndexOutOfBoundsException(final int index, final int arraySize) {
        super("Index = " + index + ", array.length = " + arraySize);
    }
}
