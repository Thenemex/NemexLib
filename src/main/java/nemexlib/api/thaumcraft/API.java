package nemexlib.api.thaumcraft;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nemexlib.api.thaumcraft.research.Research;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import nemexlib.api.util.exceptions.ResearchDoesNotExists;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;

import static thaumcraft.api.research.ResearchCategories.registerCategory;
import static thaumcraft.api.research.ResearchCategories.researchCategories;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class API {

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
        return new Research(tag, tab, aspects, x, y, complexity, icon);
    }

    /**
     * Get the research from the Thaumcraft 4 registry
     * @param tab Thaumonomicon Tab
     * @param tag Research's Tag
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
}
