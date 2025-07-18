package nemexlib.api.util.exceptions;

public class FieldIsNull extends TCRException {

    public FieldIsNull() {
        super("The field that the code is trying to get is null.");
    }
}
