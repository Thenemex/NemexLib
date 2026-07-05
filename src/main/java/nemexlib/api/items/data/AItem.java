package nemexlib.api.items.data;

import cpw.mods.fml.common.registry.GameRegistry;
import nemexlib.NemexLib;
import nemexlib.api.util.exceptions.IllegalStackSizeValue;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public abstract class AItem extends Item implements IITem {

    /**
     * Constructor with the unlocalized name only, then registers it
     * <p>The texture name will be <code>nemexlib.modID</code> <code>:</code> <code>unlocalizedName</code></p>
     * <p>No creative tab has been set</p>
     * <p>Default max stack size set at 64</p>
     * @param unlocalizedName The unlocalized name
     */
    public AItem(String unlocalizedName) {
        this(unlocalizedName, 64);
    }
    /**
     * Constructor with the unlocalized name & texture name, then registers it
     * <p>No creative tab has been set</p>
     * <p>Default max stack size set at 64</p>
     * @param unlocalizedName The unlocalized name
     * @param textureName The texture name
     */
    public AItem(String unlocalizedName, String textureName) {
        this(unlocalizedName, textureName, null);
    }
    /**
     * Constructor with the unlocalized name & creative tab, then registers it
     * <p>The texture name will be <code>nemexlib.modID</code> <code>:</code> <code>unlocalizedName</code></p>
     * <p>Default max stack size set at 64</p>
     * @param unlocalizedName The unlocalized name
     * @param tab The creative tab
     */
    public AItem(String unlocalizedName, CreativeTabs tab) {
        this(unlocalizedName, tab, 64);
    }
    /**
     * Constructor with the unlocalized name & max stack size, then registers it
     * <p>The texture name will be <code>nemexlib.modID</code> <code>:</code> <code>unlocalizedName</code></p>
     * <p>No creative tab has been set</p>
     * @param unlocalizedName The unlocalized name
     * @param maxStackSize The max stack size
     */
    public AItem(String unlocalizedName, int maxStackSize) {
        this(unlocalizedName, (CreativeTabs) null, maxStackSize);
    }
    /**
     * Constructor with the unlocalized name, creative tab & max stack size, then registers it
     * <p>The texture name will be <code>nemexlib.modID</code> <code>:</code> <code>unlocalizedName</code></p>
     * @param unlocalizedName The unlocalized name
     * @param tab The creative tab
     * @param maxStackSize The max stack size
     */
    public AItem(String unlocalizedName, CreativeTabs tab, int maxStackSize) {
        this(unlocalizedName, NemexLib.modID.toLowerCase().concat(":").concat(unlocalizedName), tab, 64);
    }
    /**
     * Constructor with the unlocalized name, texture name & max stack size, then registers it
     * <p>No creative tab has been set</p>
     * @param unlocalizedName The unlocalized name
     * @param textureName The texture name
     * @param maxStackSize The max stack size
     */
    public AItem(String unlocalizedName, String textureName, int maxStackSize) {
        this(unlocalizedName, textureName, null, maxStackSize);
    }
    /**
     * Constructor with the unlocalized name, texture name & creative tab, then registers it
     * <p>Default max stack size set at 64</p>
     * @param unlocalizedName The unlocalized name
     * @param textureName The texture name
     * @param tab The creative tab
     */
    public AItem(String unlocalizedName, String textureName, CreativeTabs tab) {
        this(unlocalizedName, textureName, tab, 64);
    }
    /**
     * Constructor with full parameters, then registers it
     * @param unlocalizedName The unlocalized name
     * @param textureName The texture name
     * @param tab The creative tab
     * @param maxStackSize The max stack size
     * @throws ParameterIsNullOrEmpty If <code>unlocalizedName</code> or <code>textureName</code> are null or empty
     * @throws IllegalStackSizeValue If <code>maxStackSize</code> is not contained between 1 and 64
     */
    public AItem(String unlocalizedName, String textureName, CreativeTabs tab, int maxStackSize) {
        if (unlocalizedName == null || unlocalizedName.isEmpty() || textureName == null || textureName.isEmpty()) throw new ParameterIsNullOrEmpty();
        if (tab != null) this.setCreativeTab(tab);
        if (maxStackSize < 1 || maxStackSize > 64) throw new IllegalStackSizeValue(maxStackSize);
        else this.setMaxStackSize(maxStackSize);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(textureName);
    }

    @Override public IITem register() {
        GameRegistry.registerItem(this, getUnlocalizedName());
        return this;
    }
    @Override public IITem setFull3D(boolean value) {
        this.bFull3D = value;
        return this;
    }
}
