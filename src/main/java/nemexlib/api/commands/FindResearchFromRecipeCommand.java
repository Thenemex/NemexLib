package nemexlib.api.commands;

import nemexlib.api.items.ItemFinder;
import nemexlib.api.recipes.crucible.CrucibleFinder;
import nemexlib.api.util.exceptions.BlockOrItemDoesNotExist;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import thaumcraft.api.ThaumcraftApi;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("rawtypes")
public class FindResearchFromRecipeCommand extends NemexLibCommand {

    protected static final String word1 = "find-research-from-recipe", word2 = "crucible", word3 = "output";

    protected final CrucibleFinder finder;

    public FindResearchFromRecipeCommand() {
        super(85);
        this.finder = new CrucibleFinder(ThaumcraftApi.getCraftingRecipes());
        this.sb.append("/").append(getCommandName())
                .append(" ").append(word1)
                .append(" ").append(word2)
                .append(" ").append(word3)
                .append(" <ITEM> [AMOUNT]");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return sb.toString();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 4 && args[0].equalsIgnoreCase(word1) && args[1].equalsIgnoreCase(word2) && args[2].equalsIgnoreCase(word3)) {
            // Item output extraction
            ItemStack output = null;
            try {
                String[] split = args[3].split(":");
                // Item from vanilla
                if (split.length == 1) output = ItemFinder.findItem("minecraft", args[3]);
                // Item with mod origin
                else if (split.length == 2) output = ItemFinder.findItem(split[0], split[1]);
                // Item with mod origin and metadata
                else if (split.length == 3) output = ItemFinder.findItem(split[0], split[1], Integer.parseInt(split[2]));
                if (output == null) throw new BlockOrItemDoesNotExist();
                // Amount parameter
                if (args.length == 5) {
                    int amount = Integer.parseInt(args[4]);
                    if (amount >= 1 && amount <= 64) output.stackSize = amount;
                }
            } catch (BlockOrItemDoesNotExist e) {
                chat(sender, "§c Item format is incorrect or item doesn't exist ! Please use §csomething like <feather> or <minecraft:feather> or <mod:item:1>");
            } catch (NumberFormatException e) {
                chat(sender, "§c The metadata/amount must be a number !");
            }
            // Researches from recipes extraction
            if (output != null) {
                if (args.length == 5) finder.findRecipesPrecise(output);
                else finder.findRecipesMeta(output);
                chatNull(sender, Arrays.toString(finder.getResearchesFromLastFoundRecipes()));
            }
        } else throw new WrongUsageException(getCommandUsage(sender));
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        switch (args.length) {
            case 1: return getListOfStringsMatchingLastWord(args, word1);
            case 2: return getListOfStringsMatchingLastWord(args, word2);
            case 3: return getListOfStringsMatchingLastWord(args, word3);
            case 4: return getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys());
        } return null;
    }

    protected void chatNull(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText("§6".concat(message.equals("null") ? "No recipes found !" : message)));
    }
}
