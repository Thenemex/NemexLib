package nemexlib.model.items;

import nemexlib.api.items.data.AItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class DebugStick extends AItem {

    private boolean called = false;

    public DebugStick() {
        super("debug_stick", CreativeTabs.tabTools, 1);
        this.setFull3D(true);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        // Trick to call it only once and not twice
        if (!called) {
            NBTTagCompound nbt = new NBTTagCompound();
            entity.writeToNBT(nbt);
            player.addChatMessage(new ChatComponentText("§6Entity : §r".concat(entity.getCommandSenderName())));
            // ToDo Print to output file
            player.addChatMessage(new ChatComponentText("§2 - NBT = §r".concat(nbt.toString())));
            called = true;
        } else called = false;
        // Won't damage the entity
        return true;
    }
}
