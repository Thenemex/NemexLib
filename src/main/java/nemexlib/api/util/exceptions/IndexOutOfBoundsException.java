package nemexlib.api.util.exceptions;

public class IndexOutOfBoundsException extends TCRException {

    public IndexOutOfBoundsException(int index, int arraySize) {
        super("Index = " + index + ", array.length = " + arraySize);
    }
}
