package nemexlib.api.items;

import cpw.mods.fml.common.registry.GameRegistry;
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
     * Will search item at the GameRegistry
     * @param mod modID
     * @param itemName Item name
     * @param meta Item Metadata
     * @return The ItemStack with the item, meta, and amount set to 1
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static ItemStack findItem(String mod, String itemName, int meta) {
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
     * @throws BlockOrItemDoesNotExist If the item doesn't exist or can't be found
     */
    public static BlockType findBlock(String mod, String blockName, int meta) {
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
