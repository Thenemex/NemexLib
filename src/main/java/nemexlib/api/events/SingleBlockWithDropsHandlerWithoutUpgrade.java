package nemexlib.api.events;

import nemexlib.api.items.types.BlockType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.common.items.wands.ItemWandCasting;

public abstract class SingleBlockWithDropsHandlerWithoutUpgrade extends WandEventHandler {

    public SingleBlockWithDropsHandlerWithoutUpgrade(BlockType block) {
        super(block);
    }
    public SingleBlockWithDropsHandlerWithoutUpgrade(String tag, BlockType block) {
        this(block);
        this.setTag(tag);
    }
    public SingleBlockWithDropsHandlerWithoutUpgrade(BlockType ... blocks) {
        super(blocks);
    }
    public SingleBlockWithDropsHandlerWithoutUpgrade(String tag, BlockType ... blocks) {
        this(blocks);
        this.setTag(tag);
    }

    /**
     * Code executed when registered trigger block is matched
     * @param world  The world
     * @param wand   The held wand item
     * @param player The Player
     * @param x      The x coordinate of the targeted block
     * @param y      The y coordinate of the targeted block
     * @param z      The z coordinate of the targeted block
     * @param side   The side of the targeted block
     * @param event  The event unique number
     * @return True is the trigger have been executed properly, false if not
     */
    @Override
    public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event) {
        return dropItem(world, wand, player, x, y, z);
    }

    /**
     * Private method called by <code>performTrigger()</code>
     * <p>Mainly used for code splitting</p>
     */
    protected boolean dropItem(World world, ItemStack heldItem, EntityPlayer player, int x, int y, int z) {
        if (world.isRemote) return false;
        if (isResearchNotComplete(player, getTag())) return false; // Needs research to perform recipe
        ItemWandCasting wand = (ItemWandCasting) heldItem.getItem();
        if (wand.getFocus(heldItem) != null) return false; // Needs no focus equipped on the wand
        if (!player.isSneaking()) return false; // Player needs to be sneaking
        // Code for editing world
        if (!isVisNeeded() || (isVisNeeded() && !wand.consumeAllVisCrafting(heldItem, player, getVis(), true)))
            return false;
        world.setBlockToAir(x, y, z);
        spawnItem(world, x, y, z, getDrops());
        return true;
    }

        /**
     * Method to implement in child class.
     * <p>the triggered blocks will drop exactly the ItemStack returned.</p>
     * @return The items that will be dropped on trigger
     */
    protected abstract ItemStack getDrops();
}
