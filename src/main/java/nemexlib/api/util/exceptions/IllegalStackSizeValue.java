package nemexlib.api.util.exceptions;

public class IllegalStackSizeValue extends TCRException {
    public IllegalStackSizeValue(int value) {
        super("Stack size value of ".concat(String.valueOf(value)).concat(" is illegal ; should be between 1 and 64"));
    }
}
