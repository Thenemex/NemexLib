package nemexlib.api.util;

import nemexlib.api.util.exceptions.IndexOutOfBoundsException;
import nemexlib.api.util.exceptions.ParameterArraySizeException;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import thaumcraft.api.research.ResearchPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    private Util(){}

    public static boolean contains(String[] tab, String s) {
        if (tab == null) return false;
        for (String el : tab)
            if (el.equalsIgnoreCase(s))
                return true;
        return false;
    }

    @SafeVarargs
    public static <T> T[] deepCopyAndRemove(T[] array, T[] emptyArray, T ... toRemove) {
        if (array == null || emptyArray == null || toRemove == null || toRemove.length == 0) throw new ParameterIsNullOrEmpty();
        if (emptyArray.length != 0) throw new ParameterArraySizeException(emptyArray.length, 0);
        ArrayList<T> list = new ArrayList<>(array.length - toRemove.length);
        list.addAll(Arrays.asList(array));
        for (T s : toRemove)
            list.remove(s);
        return list.toArray(emptyArray);
    }

    public static String[] deepCopyTabAndAdd(String[] tab, String ... newElements) {
        if (tab == null || newElements == null || newElements.length == 0) throw new ParameterIsNullOrEmpty();
        ArrayList<String> list = new ArrayList<>(tab.length + newElements.length);
        list.addAll(Arrays.asList(tab));
        list.addAll(Arrays.asList(newElements));
        return list.toArray(new String[0]);
    }

    public static ResearchPage[] removeIndex(int index, ResearchPage[] array) {
        if (array == null || array.length == 0) throw new ParameterIsNullOrEmpty();
        if (index < 0 || index >= array.length) throw new IndexOutOfBoundsException(index, array.length);
        List<ResearchPage> list = new ArrayList<>(array.length - 1);
        for (int i = 0; i < array.length; i++)
            if (i != index) list.add(array[i]);
        return list.toArray(new ResearchPage[0]);
    }

    @SafeVarargs
    public static <T> T[] addEntries(T[] array, T ... toAdd) {
        if (array == null || toAdd == null || toAdd.length == 0) throw new ParameterIsNullOrEmpty();
        ArrayList<T> list = new ArrayList<>(array.length + toAdd.length);
        list.addAll(Arrays.asList(array));
        list.addAll(Arrays.asList(toAdd));
        return list.toArray(array);
    }
}
