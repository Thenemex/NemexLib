package nemexlib.api.util.exceptions;

public class ResearchDoesNotExists extends TCRException {

    public ResearchDoesNotExists(final String tag) {
        super("The research with tag \"" + tag + "\" cannot be found.");
    }
    public ResearchDoesNotExists(final String tab, final String tag) {
        super("The research with tag \"" + tag + "\", in tab \"" + tab + "\" cannot be found.");
    }
}
