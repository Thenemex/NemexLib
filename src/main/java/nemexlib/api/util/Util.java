package nemexlib.api.util;

import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;

public class Util {

    public static String[] deepCopyTabAndAdd(String[] tab, String... newElements) {
        if (tab == null) throw new ParameterIsNullOrEmpty();
        String[] deepCopy = new String[tab.length + newElements.length];
        System.arraycopy(tab, 0, deepCopy, 0, tab.length);
        System.arraycopy(newElements, 0, deepCopy, deepCopy.length - newElements.length - 1, newElements.length);
        return deepCopy;
    }
}
