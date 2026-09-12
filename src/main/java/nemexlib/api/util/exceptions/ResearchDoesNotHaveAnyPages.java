package nemexlib.api.util.exceptions;

public class ResearchDoesNotHaveAnyPages extends TCRException {

    public ResearchDoesNotHaveAnyPages(final String tag) {
        super("The research \"".concat(tag).concat("\" does not have any pages"));
    }
}
