package nemexlib.api.thaumcraft.research;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nemexlib.api.events.WandEventHandler;
import nemexlib.api.thaumcraft.API;
import nemexlib.api.thaumcraft.aspects.Aspects;
import nemexlib.api.util.exceptions.FieldIsNull;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchPage;

import java.util.Arrays;

@SuppressWarnings({"UnusedReturnValue", "unused", "EmptyMethod"})
public abstract class AResearch {

    protected static boolean expert = false;
    protected final String tab, tag;
    protected final ItemStack icon;
    protected WandEventHandler handler;
    protected Aspects aspects;
    protected Research research;

    public AResearch(final String tab, final String tag, final ItemStack icon) {
        this.tab = tab;
        this.tag = tag;
        this.icon = icon;
        removeRecipes();
        init();
    }

    public AResearch(final String tab, final String tag, final Block block) {
        this(tab, tag, new ItemStack(block));
    }
    public AResearch(final String tab, final String tag, final Block block, final int meta) {
        this(tab, tag, new ItemStack(block, 1, meta));
    }
    public AResearch(final String tab, final String tag, final Item item) {
        this(tab, tag, new ItemStack(item));
    }
    public AResearch(final String tab, final String tag, final Item item, final int meta) {
        this(tab, tag, new ItemStack(item, 1, meta));
    }

    public abstract void init();
    public abstract void setResearchProperties();
    public void removeRecipes() {}

    public String getTab() {
        return tab;
    }
    public String getTag() {
        return tag;
    }

    public Aspects getAspects() throws FieldIsNull {
        if (aspects == null) throw new FieldIsNull();
        return aspects;
    }
    public AResearch setResearchAspects(final Aspect aspect, final int amount) {
        this.aspects = new Aspects(aspect, amount);
        return this;
    }
    public AResearch setResearchAspects(final Aspect[] aspects, final int ... amounts) {
        this.aspects = new Aspects(aspects, amounts);
        return this;
    }
    public AResearch setResearchAspects(final Aspects aspects) {
        this.aspects = aspects;
        return this;
    }

    public AResearch setNewResearch(final int x, final int y, final int complexity) {
        this.research = API.newResearch(tag, tab, aspects, x, y, complexity, icon);
        this.setResearchProperties();
        return this.register();
    }
    public AResearch setNewResearch(final int x, final int y) {
        return setNewResearch(x, y, 1);
    }

    public ResearchPage[] getPages() {
        return research.getPages();
    }
    public AResearch setPages(final ResearchPage ... pages) {
        this.research.setPages(pages);
        return this;
    }
    public AResearch addPage(final ResearchPage ... pages) {
        if (pages == null || pages.length == 0) throw new ParameterIsNullOrEmpty();
        int oldLength = getNbPages();
        int newLength = oldLength + pages.length;
        ResearchPage[] newPages = Arrays.copyOf(research.getPages(), newLength);
        System.arraycopy(pages, 0, newPages, oldLength, pages.length);
        this.setPages(newPages);
        return this;
    }
    public int getNbPages() {
        return research.getPages().length;
    }
    public ResearchPage newTextPage(final int number) {
        return new ResearchPage(research.getPageTag(number));
    }

    public WandEventHandler getHandler() throws FieldIsNull {
        if (handler == null) throw new FieldIsNull();
        return handler;
    }
    public AResearch setHandler(final WandEventHandler handler) {
        this.handler = handler;
        return this;
    }

    protected AResearch register() {
        research.registerResearchItem();
        return this;
    }
}
