package nemexlib.api.util.comparators;

import thaumcraft.api.research.ResearchItem;

import java.util.Comparator;

import static thaumcraft.api.research.ResearchCategories.getCategoryName;

public class ResearchComparator implements Comparator<ResearchItem> {

    @Override public int compare(ResearchItem x, ResearchItem y) {
        if (getCategoryName(x.category).equalsIgnoreCase(getCategoryName(y.category)))
            return x.getName().compareToIgnoreCase(y.getName());
        else return getCategoryName(x.category).compareToIgnoreCase(getCategoryName(y.category));
    }
}
