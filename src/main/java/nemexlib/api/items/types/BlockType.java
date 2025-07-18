package nemexlib.api.items.types;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.Objects;

/**
 * Class used for storing the Block and it's metadata.
 * <p>Can be useful when you need an object similar to ItemStack, but for Blocks.</p>
 */
@SuppressWarnings("unused")
public class BlockType {

    protected final Block block;
    protected final int meta;

    /**
     * Constructor for the class
     * <p>Set meta to 0</p>
     * @param block The block
     */
    public BlockType(Block block) {
        this.block = block;
        this.meta = 0;
    }
    /**
     * Constructor for the class
     * @param block The block
     * @param meta The metadata
     */
    public BlockType(Block block, int meta) {
        this.block = block;
        this.meta = Math.max(meta, 0);
    }

    /**
     * Getter for the block
     * @return The block
     */
    public Block block() {
        return block;
    }
    /**
     * Getter for the metadata
     * @return The metadata
     */
    public int meta() {
        return meta;
    }

    /**
     * Return the ItemStack related to the BlockType
     * @return The ItemStack, with block and meta
     */
    public ItemStack toItemStack() {
        return new ItemStack(block(), 1, meta());
    }
    /**
     * Return the ItemStack related to the BlockType
     * @param amount The amount inside the ItemStack
     * @return The ItemStack, with block, set amount and meta
     */
    public ItemStack toItemStack(int amount) {
        return new ItemStack(block(), amount, meta());
    }

    @Override public String toString() {
        return getClass().getSimpleName().concat("{block = " + block() + ", meta = " + meta + "}");
    }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockType)) return false;
        BlockType blockType = (BlockType) o;
        return meta == blockType.meta && Objects.equals(block, blockType.block);
    }
    @Override public int hashCode() {
        return Objects.hash(block, meta);
    }
}
