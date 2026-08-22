package nemexlib.api.items.thaumcraft;

import nemexlib.api.util.exceptions.ParameterArraySizeException;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.wands.ItemFocusBasic;

public class FocusMaker {

    public static ItemStack make(ItemFocusBasic focus, int ... upgrades) {
        if (upgrades == null || upgrades.length == 0) throw new ParameterIsNullOrEmpty();
        if (upgrades.length > 5) throw new ParameterArraySizeException(5);
        NBTTagCompound tags, nbt = new NBTTagCompound();
        NBTTagList nbtList = new NBTTagList();
        nbt.setTag("upgrade", nbtList);
        for (int u : upgrades) {
            tags = new NBTTagCompound();
            tags.setShort("id", (short) u);
            nbtList.appendTag(tags);
        }
        ItemStack upFocus = new ItemStack(focus);
        upFocus.setTagCompound(nbt);
        return upFocus;
    }
}
