package nemexlib.api.thaumcraft.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;

@SuppressWarnings("unused")
public class Research extends ResearchItem {

    /**
     * This field can be edited to change the research unlocalized prefix
     */
    public static String modID;

    /**
     * Create a new research in the Thaumonomicon, and registers it
     * @param tag Research tag
     * @param tab Thaumonomicon tab
     * @param aspects Research Aspects
     * @param x Row position
     * @param y Column position
     * @param complexity Research complexity
     * @param icon The item's icon
     */
    public Research(final String tag, final String tab, final AspectList aspects, final int x, final int y, final int complexity, final ItemStack icon) {
        super(tag, tab, aspects, x, y, complexity, icon);
    }

    /**
     * Adds warp to a research
     * @param warp The amount of warp
     */
    public Research addWarp(final int warp) {
        ThaumcraftApi.addWarpToResearch(key, warp);
        return this;
    }

    @Override
    public String getName() {
        return StatCollector.translateToLocal(getPrefix().concat("name.").concat(key));
    }
    @Override
    public String getText() {
        return StatCollector.translateToLocal(getPrefix().concat("text.").concat(key));
    }

    public String getPageTag(final int number) {
        return getPrefix().concat("page.").concat(key).concat(".").concat(String.valueOf(number));
    }

    private static String getPrefix() {
        if (modID == null) modID = "mod";
        return modID.toLowerCase().concat("_research_");
    }
}
