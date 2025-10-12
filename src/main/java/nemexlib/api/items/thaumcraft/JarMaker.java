package nemexlib.api.items.thaumcraft;

import nemexlib.api.util.exceptions.EssentiaAmountIsWrong;
import nemexlib.api.util.exceptions.ParameterArraysSizeException;
import nemexlib.api.util.exceptions.ParameterIsNullOrEmpty;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static nemexlib.api.items.ItemFinder.findItemTC;

public class JarMaker {

    public static ItemStack make(Aspect aspect, int amount) {
        if (aspect == null) throw new ParameterIsNullOrEmpty();
        // ItemJarFilled jar = ((ItemJarFilled) ConfigItems.itemJarFilled).setAspects(jar, new Aspects(CRYSTAL, 64));
        NBTTagCompound nbt = new NBTTagCompound(), tags = new NBTTagCompound();
        NBTTagList nbtList = new NBTTagList();
        nbt.setTag("Aspects", nbtList);
        tags.setInteger("amount", amount);
        tags.setString("key", aspect.getName().toLowerCase());
        nbtList.appendTag(tags);
        ItemStack jar = findItemTC("BlockJarFilledItem");
        jar.setTagCompound(nbt);
        return jar;
    }

    public static ItemStack make(AspectList aspect) {
        if (aspect == null) throw new ParameterIsNullOrEmpty();
        if (aspect.aspects.size() != 1) throw new ParameterArraysSizeException(aspect.aspects.size(), 1);
        if (aspect.visSize() < 1 || aspect.visSize() > 64) throw new EssentiaAmountIsWrong(1, 64, aspect.visSize());
        return make(aspect.getAspects()[0], aspect.visSize());
    }
}
