package nemexlib.api.util.exceptions;

public class TabDoesNotExists extends TCRException {

    public TabDoesNotExists(String tab) {
        super("Cannot get researchCategory for name : \"".concat(tab).concat("\""));
    }
}
