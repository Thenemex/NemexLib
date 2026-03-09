package nemexlib.api.util.exceptions;

public class ResearchRemovalException extends TCRException {

    public ResearchRemovalException(String tab, String key) {
        super("Cannot remove research tag \"".concat(key)
                .concat("\" from tab \"".concat(tab).concat("\"")));
    }
}
