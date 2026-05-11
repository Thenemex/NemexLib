package nemexlib.api.util.exceptions;

public class ResearchDoesNotExists extends TCRException {

    public ResearchDoesNotExists(String tag) {
        super("The research with tag \"" + tag + "\" cannot be found.");
    }
    public ResearchDoesNotExists(String tab, String tag) {
        super("The research with tag \"" + tag + "\", in tab \"" + tab + "\" cannot be found.");
    }
}
