package nemexlib.api.items;

import cpw.mods.fml.common.registry.GameRegistry;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nemexlib.api.items.types.BlockType;
import nemexlib.api.util.exceptions.BlockOrItemDoesNotExist;
import thaumcraft.common.Thaumcraft;

/**
 * Class used for finding items or blocks registered to the Forge GameRegistry
 * <p>Items/Blocks are in the same format as in game. The format is : <code>mod : itemName : meta</code></p>
 * <p>Exemple : <code>minecraft : diamond</code>, or <code>Thaumcraft : blockMagicalLog : 1</code></p>
 */
@SuppressWarnings("unused")
public class ItemFinder {

    private ItemFinder(){}

    /**
     * Return the itemstack associated with the tagName
     * <p>Exemple of tag names : <code>feather</code>, <code>minecraft:arrow</code>, <code>Thaumcraft:itemShard:3</code></p>
     * @param tagName The full tag name
     * @return The ItemStack associated with the tag name
     * @throws ParameterIsNullOrEmpty The parameter is null or empty
     * @throws BlockOrItemDoesNotExist The item doesn't exist
     * @throws NumberFormatException The metadata must be a number
     */
    public static ItemStack getItem(String tagName) {
        if (tagName == null || tagName.isEmpty()) throw new ParameterIsNullOrEmpty();
        String[] split = tagName.split(":");
        switch (split.length) {
            // Item from vanilla
            case 1: return ItemFinder.findItem("minecraft", split[0]);
            // Item with mod origin
            case 2: return ItemFinder.findItem(split[0], split[1]);
            // Item with mod origin and metadata
            case 3: return ItemFinder.findItem(split[0], split[1], Integer.parseInt(split[2]));
            // Any other odd input
            default: return null;
        }
    }

    /**
     * Will search item at the GameRegistry
     * @param mod modID
     * @param itemName Item name
     * @param meta Item Metadata
     * @return The ItemStack with the item, meta, and amount set to 1
     * @throws ParameterIsNullOrEmpty One of the parameters is null or empty
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static ItemStack findItem(String mod, String itemName, int meta) {
        if (mod == null || mod.isEmpty() || itemName == null || itemName.isEmpty()) throw new ParameterIsNullOrEmpty();
        Item item = GameRegistry.findItem(mod, itemName);
        if (item == null) throw new BlockOrItemDoesNotExist(mod, itemName, meta);
        return new ItemStack(item, 1 , meta);
    }
    /**
     * Will search item at the GameRegistry
     * @param mod modID
     * @param itemName Item name
     * @return The ItemStack with the item, meta set to 0, and amount set to 1
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static ItemStack findItem(String mod, String itemName) {
        return findItem(mod, itemName, 0);
    }

    /**
     * Will search item from Thaumcraft 4 at the GameRegistry
     * @param itemName Item name
     * @param meta Item Metadata
     * @return The ItemStack with the item, meta, and amount set to 1
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static ItemStack findItemTC(String itemName, int meta) {
        return findItem(Thaumcraft.MODID, itemName, meta);
    }
    /**
     * Will search item from Thaumcraft 4 at the GameRegistry
     * @param itemName Item name
     * @return The ItemStack with the item, meta set to 0, and amount set to 1
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static ItemStack findItemTC(String itemName) {
        return findItemTC(itemName, 0);
    }

    /**
     * Will search block at the GameRegistry
     * @param mod modID
     * @param blockName Item name
     * @param meta Item Metadata
     * @return The BlockType containing the item and its metadata
     * @throws ParameterIsNullOrEmpty One of the parameters is null or empty
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static BlockType findBlock(String mod, String blockName, int meta) {
        if (mod == null || mod.isEmpty() || blockName == null || blockName.isEmpty()) throw new ParameterIsNullOrEmpty();
        Block block = GameRegistry.findBlock(mod, blockName);
        if (block == null) throw new BlockOrItemDoesNotExist(mod, blockName, meta);
        return new BlockType(block, meta);
    }
    /**
     * Will search block at the GameRegistry
     * @param mod modID
     * @param blockName Item name
     * @return The BlockType containing the item and its metadata set 0
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static BlockType findBlock(String mod, String blockName) {
        return findBlock(mod, blockName, 0);
    }

    /**
     * Will search block from Thaumcraft 4 at the GameRegistry
     * @param blockName Item name
     * @param meta Item Metadata
     * @return The BlockType containing the item and its metadata
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static BlockType findBlockTC(String blockName, int meta) {
        return findBlock(Thaumcraft.MODID, blockName, meta);
    }
    /**
     * Will search block from Thaumcraft 4 at the GameRegistry
     * @param blockName Item name
     * @return The BlockType containing the item and its metadata set to 0
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static BlockType findBlockTC(String blockName) {
        return findBlockTC(blockName, 0);
    }
}
