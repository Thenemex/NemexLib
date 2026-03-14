package nemexlib.api.util;

import nemexlib.api.util.exceptions.IndexOutOfBoundsException;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import thaumcraft.api.research.ResearchPage;

import java.util.Arrays;
import java.util.List;

public class Util {

    private Util(){}

    public static boolean contains(String[] tab, String s) {
        for (String el : tab)
            if (el.equalsIgnoreCase(s))
                return true;
        return false;
    }

    public static String[] deepCopyAndRemove(String[] tab, String ... toRemove) {
        if (tab == null || toRemove == null || toRemove.length == 0) throw new ParameterIsNullOrEmpty();
        List<String> list = Arrays.asList(tab);
        for (String s : toRemove)
            list.remove(s);
        return list.toArray(new String[0]);
    }

    public static String[] deepCopyTabAndAdd(String[] tab, String ... newElements) {
        if (tab == null || newElements == null || newElements.length == 0) throw new ParameterIsNullOrEmpty();
        List<String> list = Arrays.asList(tab);
        list.addAll(Arrays.asList(newElements));
        return list.toArray(new String[0]);
    }

    public static ResearchPage[] removeIndex(int index, ResearchPage[] array) {
        if (array == null || array.length == 0) throw new ParameterIsNullOrEmpty();
        if (index < 0 || index >= array.length) throw new IndexOutOfBoundsException(index, array.length);
        List<ResearchPage> list = Arrays.asList(array);
        list.remove(index);
        return list.toArray(new ResearchPage[0]);
    }
}
