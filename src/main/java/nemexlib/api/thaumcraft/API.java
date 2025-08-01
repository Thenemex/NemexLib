package nemexlib.api.thaumcraft;

import nemexlib.api.util.Util;
import nemexlib.api.util.exceptions.IndexOutOfBoundsException;
import nemexlib.api.util.exceptions.ResearchDoesNotHaveAnyPages;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nemexlib.api.thaumcraft.research.Research;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import nemexlib.api.util.exceptions.ResearchDoesNotExists;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import java.util.ArrayList;
import java.util.Arrays;

import static thaumcraft.api.research.ResearchCategories.registerCategory;
import static thaumcraft.api.research.ResearchCategories.researchCategories;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class API {

    private API(){}

    /**
     * Create a new tab in the Thaumonomicon, with the default TC4 background
     * @param tag Tab tag
     * @param icon The icon
     */
    public static void newCategory(String tag, ResourceLocation icon) {
        registerCategory(tag, icon, new ResourceLocation("thaumcraft","textures/gui/gui_researchback.png"));
    }

    /**
     * Create a new research in the Thaumonomicon, and registers it
     * @param tag Research tag
     * @param tab Thaumonomicon tab
     * @param aspects Research Aspects
     * @param x Row position
     * @param y Column position
     * @param complexity Research complexity
     * @param icon The item's icon
     * @return The research
     */
    public static Research newResearch(String tag, String tab, AspectList aspects, int x, int y, int complexity, ItemStack icon) {
        if (tag == null || tab == null || icon == null) throw new ParameterIsNullOrEmpty();
        return new Research(tag, tab, aspects, x, y, complexity, icon);
    }

    /**
     * Get the research from the Thaumcraft 4 registry
     * @param tab Thaumonomicon Tab
     * @param tag Research tag/key
     * @return The research
     * @throws ParameterIsNullOrEmpty If one of the parameters is null
     * @throws ResearchDoesNotExists If no research with such tab and tag is found
     */
    public static ResearchItem getResearch(String tab, String tag) {
        if (tab == null || tag == null) throw new ParameterIsNullOrEmpty();
        ResearchItem research = researchCategories.get(tab).research.get(tag);
        if (research == null) throw new ResearchDoesNotExists(tab, tag);
        return research;
    }

    /**
     * Allows to add prereqs to a research
     * @param tab The Thaumonomicon tab/category
     * @param tag The research tab/key
     * @param hidden Is the parents hidden or not
     * @param parents The parents to add
     * @throws ParameterIsNullOrEmpty If one of the parameter is null, or that the array is empty
     * @throws ResearchDoesNotExists If no research with such tab and tag is found
     */
    public static void addParents(String tab, String tag, boolean hidden, String ... parents) {
        if (parents == null || parents.length == 0) throw new ParameterIsNullOrEmpty();
        addParents(getResearch(tab, tag), hidden, parents);
    }
    /**
     * Allows to add prereqs to a research
     * @param research The research
     * @param hidden Is the parentsToAdd hidden or not
     * @param parentsToAdd The parentsToAdd to add
     * @throws ParameterIsNullOrEmpty If one of the parameter is null, or that the array is empty
     */
    public static void addParents(ResearchItem research, boolean hidden, String ... parentsToAdd) {
        if (research == null || parentsToAdd == null) throw new ParameterIsNullOrEmpty();
        if (hidden) {
            if (research.parentsHidden == null)
                research.setParentsHidden(parentsToAdd);
            else
                research.setParentsHidden(Util.deepCopyTabAndAdd(research.parentsHidden, parentsToAdd));
        } else {
            if (research.parents == null)
                research.setParents(parentsToAdd);
            else
                research.setParents(Util.deepCopyTabAndAdd(research.parents, parentsToAdd));
        }
    }

    /**
     * Add pages at the end of the research pages
     * <p>If the research pages is null or empty, it will replace the whole pages with the pages to add</p>
     * @param tab Thaumonomicon tab
     * @param tag Research tag/key
     * @param pagesToAdd The pages to add
     * @throws ParameterIsNullOrEmpty If one of the parameters is null
     */
    public static void addPage(String tab, String tag, ResearchPage ... pagesToAdd) {
        if (tab == null || tag == null) throw new ParameterIsNullOrEmpty();
        addPage(getResearch(tab, tag), pagesToAdd);
    }
    /**
     * Add pages at the end of the research pages
     * <p>If the research pages is null or empty, it will replace the whole pages with the pages to add</p>
     * @param research The research
     * @param pagesToAdd The pages to add
     * @throws ParameterIsNullOrEmpty If one of the parameters is null
     */
    public static void addPage(ResearchItem research, ResearchPage ... pagesToAdd) {
        if (research == null || pagesToAdd == null) throw new ParameterIsNullOrEmpty();
        if (pagesToAdd.length == 0) throw new ParameterIsNullOrEmpty();
        if (research.getPages() == null) research.setPages(pagesToAdd);
        else if (research.getPages().length == 0) research.setPages(pagesToAdd);
        else {
            ResearchPage[] pages = research.getPages();
            ResearchPage[] newPages = new ResearchPage[pages.length + pagesToAdd.length];
            // Copying all pages from the research pages
            System.arraycopy(pages, 0, newPages, 0, pages.length);
            // Adding all the pages to add at the end of the array
            System.arraycopy(pagesToAdd, 0, newPages, pages.length, newPages.length);
            research.setPages(newPages);
        }
    }

    /**
     * Fits a page at the given index.
     * <p>All researches from that index value will be pushed at slot+1</p>
     * <p>Ex : If a research got 2 pages, and you wanted to add the page at index 2 :</p>
     * <p>Page at slot 1 will stay 1, the new page will go at slot 2, and the actual page at slot 2 will go at slot 3</p>
     * @param tab Thaumonomicon tab
     * @param tag Research tag/key
     * @param pageToAdd The page to fit at index
     * @param index The index at which the page will be removed
     * <p>NB: The index start from 1 and not 0</p>
     * @throws ParameterIsNullOrEmpty If one of the parameters is null
     * @throws ResearchDoesNotHaveAnyPages If the research pages is null or empty
     * @throws IndexOutOfBoundsException If the index exceeds the size of the pages array of more than +1
     */
    public static void addPage(String tab, String tag, ResearchPage pageToAdd, int index) {
        if (tab == null || tag == null) throw new ParameterIsNullOrEmpty();
        addPage(getResearch(tab, tag), pageToAdd, index);
    }
    /**
     * Fits a page at the given index.
     * <p>All researches from that index value will be pushed at slot+1</p>
     * <p>Ex : If a research got 2 pages, and you wanted to add the page at index 2 :</p>
     * <p>Page at slot 1 will stay 1, the new page will go at slot 2, and the actual page at slot 2 will go at slot 3</p>
     * @param research The research
     * @param pageToAdd The page to fit at index
     * @param index The index at which the page will be removed
     * <p>NB: The index start from 1 and not 0</p>
     * @throws ParameterIsNullOrEmpty If one of the parameters is null
     * @throws ResearchDoesNotHaveAnyPages If the research pages is null or empty
     * @throws IndexOutOfBoundsException If the index exceeds the size of the pages array of more than +1
     */
    public static void addPage(ResearchItem research, ResearchPage pageToAdd, int index) {
        if (research == null || pageToAdd == null) throw new ParameterIsNullOrEmpty();
        if (research.getPages() == null) throw new ResearchDoesNotHaveAnyPages(research.key);
        ResearchPage[] pages = research.getPages();
        if (pages.length == 0) throw new ResearchDoesNotHaveAnyPages(research.key);
        if (index < 1 || index > pages.length + 1) throw new IndexOutOfBoundsException(index, pages.length);
        ArrayList<ResearchPage> listPages = new ArrayList<>(pages.length + 1);
        listPages.addAll(Arrays.asList(pages));
        listPages.add(index - 1, pageToAdd);
        research.setPages(listPages.toArray(listPages.toArray(new ResearchPage[pages.length + 1])));
    }

    /**
     * Remove a page from a research
     * @param tab Thaumonomicon tab
     * @param tag Research tag/key
     * @param index The index at which the page will be removed
     * <p>NB: The index start from 1 and not 0</p>
     * @return The page removed from the research pages, in case you need it
     * @throws ParameterIsNullOrEmpty If research is null
     * @throws IndexOutOfBoundsException If the index is not matching any slot in the research pages array
     */
    public static ResearchPage removePage(String tab, String tag, int index) {
        if (tab == null || tag == null) throw new ParameterIsNullOrEmpty();
        return removePage(getResearch(tab, tag), index);
    }
    /**
     * Remove a page from a research
     * @param research The research
     * @param index The index at which the page will be removed
     * <p>NB: The index start from 1 and not 0</p>
     * @return The page removed from the research pages, in case you need it
     * @throws ParameterIsNullOrEmpty If research is null
     * @throws ResearchDoesNotHaveAnyPages If the research pages is null or empty
     * @throws IndexOutOfBoundsException If the index is not matching any slot in the research pages array
     */
    public static ResearchPage removePage(ResearchItem research, int index) {
        if (research == null) throw new ParameterIsNullOrEmpty();
        if (research.getPages() == null) throw new ResearchDoesNotHaveAnyPages(research.key);
        ResearchPage[] pages = research.getPages();
        if (pages.length == 0) throw new ResearchDoesNotHaveAnyPages(research.key);
        if (index < 1 || index > pages.length) throw new IndexOutOfBoundsException(index, pages.length);
        // All checks are done, proceeding to remove element
        ResearchPage removedPage = pages[index];
        research.setPages(Util.removeIndex(index - 1, pages));
        return removedPage;
    }

    /**
     * Replace a page by another in the research
     * @param tab Thaumonomicon tab
     * @param tag Research tag/key
     * @param pageToAdd The page to replace the one at index position
     * @param index The index at which it will replace the page
     * <p>NB: The index start from 1 and not 0</p>
     * @return The old page replaced
     * @throws ParameterIsNullOrEmpty If one of the parameters is null
     * @throws ResearchDoesNotHaveAnyPages If the research pages is null or empty
     * @throws IndexOutOfBoundsException If the index is not matching any slot in the research pages array
     */
    public static ResearchPage replacePage(String tab, String tag, ResearchPage pageToAdd, int index) {
        if (tab == null || tag == null) throw new ParameterIsNullOrEmpty();
        return replacePage(getResearch(tab, tag), pageToAdd, index);
    }
    /**
     * Replace a page by another in the research
     * @param research The research
     * @param pageToAdd The page to replace the one at index position
     * @param index The index at which it will replace the page
     * <p>NB: The index start from 1 and not 0</p>
     * @return The old page replaced
     * @throws ParameterIsNullOrEmpty If one of the parameters is null
     * @throws ResearchDoesNotHaveAnyPages If the research pages is null or empty
     * @throws IndexOutOfBoundsException If the index is not matching any slot in the research pages array
     */
    public static ResearchPage replacePage(ResearchItem research, ResearchPage pageToAdd, int index) {
        if (research == null || pageToAdd == null) throw new ParameterIsNullOrEmpty();
        if (research.getPages() == null) throw new ResearchDoesNotHaveAnyPages(research.key);
        ResearchPage[] pages = research.getPages();
        if (pages.length == 0) throw new ResearchDoesNotHaveAnyPages(research.key);
        if (index < 1 || index > pages.length) throw new IndexOutOfBoundsException(index, pages.length);
        ResearchPage pageRemoved = pages[index];
        pages[index] = pageToAdd;
        return pageRemoved;
    }
}
