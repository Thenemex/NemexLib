package nemexlib.api.events;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nemexlib.api.items.types.BlockType;
import nemexlib.api.thaumcraft.aspects.Aspects;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.lib.research.ResearchManager;

import static nemexlib.NemexLib.modID;

/**
 * Class for handling events linked to wands. Have to be instantiated in AResearch class fields.
 * <p>You extend this class, and code the two main things linked to it :</p>
 * <p> - Registering blocks + event number, for triggering the method "performTrigger()" when targeted block is matched.</p>
 * <p> - Coding the method "performTrigger()" ; this method will be called upon targeting a registered block with your wand.
 * You can code whatever you want inside : change the world blocks, drop items, summon entities, etc ... </p>
 */
@SuppressWarnings({"unused", "SameParameterValue", "UnusedReturnValue"})
public abstract class WandEventHandler implements IWandTriggerManager {

    /**
     * These fields are to be kept read-only, and should not be edited alone.
     * <p>Usage of the methods like <code>setVis()</code> are hugely advised, as it will keep the good values here</p>
     * <p>Any standalone modification can break the code, and lead to exceptions thrown when performing the wand trigger</p>
     */
    protected boolean isVisNeeded = false;
    private Aspects vis = null;

    /**
     * Constructor for handler.
     * <p>You must use <code>registerTriggerEvent()</code> in your class constructor, to make the handler work</p>
     */
    public WandEventHandler() {}
    /**
     * Constructor for handler.
     * <p>Will automatic register all blocks from argument array as triggerEvents</p>
     * <p>Each one will get a unique event number, starting from 0 and getting incremented by one with each register done.</p>
     * @param blocks The triggers to be registered
     * @throws ParameterIsNullOrEmpty If blocks is null or empty
     */
    public WandEventHandler(BlockType[] blocks) {
        registerTriggerEvent(blocks);
    }
    /**
     * Constructor for handler.
     * <p>Will automatic register all blocks from argument array as triggerEvents</p>
     * <p>Each one will get a unique event number, starting from 0 and getting incremented by one with each register done.</p>
     * @param blocks The triggers to be registered
     * @param vis The primal aspects that will be consumed on the held wand by the recipe
     * @throws ParameterIsNullOrEmpty If blocks is null or empty
     */
    public WandEventHandler(BlockType[] blocks, Aspects vis) {
        this(blocks);
        if (vis != null) setVis(vis);
    }

    /**
     * Getter for the vis aspects
     * @return If vis is not null, it will return the aspects. If it is null, it will return null;
     */
    public Aspects getVis() {
        if (isVisNeeded) return vis;
        return null;
    }
    /**
     * Setter for the vis usage
     * @param vis The aspect vis
     * @return Itself
     */
    public WandEventHandler setVis(Aspects vis) {
        if (vis != null) this.vis = vis;
        this.isVisNeeded = true;
        return this;
    }

    /**
     * Register wand triggers on blocks to the Thaumcraft 4 Registry
     * <p>Each block will be given an event number incremented at each loop</p>
     * <p>Exemple : <code>blocks[0]</code> will have event = 0, <code>block[1]</code> will have event = 1 ...</p>
     * @param blocks The blocks that trigger the event
     * @throws ParameterIsNullOrEmpty If blocks is null or empty
     */
    public void registerTriggerEvent(BlockType[] blocks) {
        if (blocks == null || blocks.length == 0) throw new ParameterIsNullOrEmpty();
        for (int i = 0; i < blocks.length; i++)
            registerTriggerEvent(blocks[i], i);
    }
    /**
     * Register wand triggers on block to the Thaumcraft 4 Registry
     * @param block The block that trigger the event
     * @param event The event number
     * @throws ParameterIsNullOrEmpty If block is null
     */
    public void registerTriggerEvent(BlockType block, int event) {
        if (block == null) throw new ParameterIsNullOrEmpty();
        WandTriggerRegistry.registerWandBlockTrigger(this, event, block.block(), block.meta(), modID);
    }
    /**
     * Register wand triggers on block to the Thaumcraft 4 Registry
     * <p>Event number will be set to 0</p>
     * @param block The block that trigger the event
     * @throws ParameterIsNullOrEmpty If blocks is null
     */
    public void registerTriggerEvent(BlockType block) {
        registerTriggerEvent(block, 0);
    }
    /**
     * Register wand triggers on block to the Thaumcraft 4 Registry
     * <p>The block metadata will be set to 0</p>
     * @param block The block that trigger the event
     * @param event The event number
     * @throws ParameterIsNullOrEmpty If block is null
     */
    public void registerTriggerEvent(Block block, int event) {
        if (block == null) throw new ParameterIsNullOrEmpty();
        WandTriggerRegistry.registerWandBlockTrigger(this, event, block, 0, modID);
    }
    /**
     * Register wand triggers on block to the Thaumcraft 4 Registry
     * <p>The block metadata AND event number will be set to 0</p>
     * @param block The block that trigger the event
     * @throws ParameterIsNullOrEmpty If block is null
     */
    public void registerTriggerEvent(Block block) {
        registerTriggerEvent(block, 0);
    }

    /**
     * Tests if the player had done a specific research
     * @param player The player
     * @param tag The research tag
     * @return True if the research is done, else false
     */
    protected boolean isResearchNotComplete(EntityPlayer player, String tag) {
        return !ResearchManager.isResearchComplete(player.getCommandSenderName(), tag);
    }

    /**
     * Code executed when registered trigger block is matched
     * @param world The world
     * @param wand The held wand item
     * @param player The Player
     * @param x The x coordinate of the targeted block
     * @param y The y coordinate of the targeted block
     * @param z The z coordinate of the targeted block
     * @param side The side of the targeted block
     * @param event The event unique number
     * @return True is the trigger have been executed properly, false if not
     */
    @Override
    public abstract boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event);
}
