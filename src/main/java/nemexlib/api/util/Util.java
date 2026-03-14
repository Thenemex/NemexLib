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
        String[] deepCopy = new String[tab.length + newElements.length];
        System.arraycopy(tab, 0, deepCopy, 0, tab.length);
        System.arraycopy(newElements, 0, deepCopy, deepCopy.length - newElements.length - 1, newElements.length);
        return deepCopy;
    }
    // ToDo Redo all code safe with ArrayList methods

    public static ResearchPage[] removeIndex(int index, ResearchPage[] array) {
        if (array == null || array.length == 0) throw new ParameterIsNullOrEmpty();
        if (index < 0 || index >= array.length) throw new IndexOutOfBoundsException(index, array.length);
        ResearchPage[] newArray = new ResearchPage[array.length - 1];
        boolean indexReached = false;
        for (int i = 0; i < array.length; i++)
            if (i != index && !indexReached) // Is not the element to remove AND element not found yet
                newArray[i] = array[i];
            else if (indexReached) // Element has been found in past iteration
                newArray[i - 1] = array[i];
            else // Element is found at current iteration
                indexReached = true;
        return newArray;
    }
}
