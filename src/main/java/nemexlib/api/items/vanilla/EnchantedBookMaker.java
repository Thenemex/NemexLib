package nemexlib.api.items.vanilla;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class EnchantedBookMaker {

    public static ItemStack make(int level, int id) {
        NBTTagCompound nbt = new NBTTagCompound(), tags = new NBTTagCompound();
        NBTTagList nbtList = new NBTTagList();
        nbt.setTag("StoredEnchantments", nbtList);
        tags.setShort("lvl", (short) level);
        tags.setShort("id", (short) id);
        nbtList.appendTag(tags);
        ItemStack book = new ItemStack(Items.enchanted_book);
        book.setTagCompound(nbt);
        return book;
    }
}
