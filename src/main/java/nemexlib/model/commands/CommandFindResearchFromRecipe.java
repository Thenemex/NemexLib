package nemexlib.model.commands;

import nemexlib.api.commands.NemexLibCommand;
import nemexlib.api.items.ItemFinder;
import nemexlib.api.recipes.crucible.CrucibleFinder;
import nemexlib.api.util.exceptions.BlockOrItemDoesNotExist;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("rawtypes")
public class CommandFindResearchFromRecipe extends NemexLibCommand {

    private static final String crucible = "crucible", output = "output";
    protected final CrucibleFinder finder;

    public CommandFindResearchFromRecipe() {
        super("find-research-from-recipe", "crucible output <ITEM> [AMOUNT]");
        this.finder = new CrucibleFinder(ThaumcraftApi.getCraftingRecipes());
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 3 && args[0].equalsIgnoreCase(crucible) && args[1].equalsIgnoreCase(output)) {
            // Item output extraction
            ItemStack output = null;
            try {
                // ToDo Make it work with IDs
                String[] split = args[2].split(":");
                // Item from vanilla
                if (split.length == 1) output = ItemFinder.findItem("minecraft", args[2]);
                // Item with mod origin
                else if (split.length == 2) output = ItemFinder.findItem(split[0], split[1]);
                // Item with mod origin and metadata
                else if (split.length == 3) output = ItemFinder.findItem(split[0], split[1], Integer.parseInt(split[2]));
                if (output == null) throw new BlockOrItemDoesNotExist();
                // Amount parameter
                if (args.length == 4) {
                    int amount = Integer.parseInt(args[3]);
                    if (amount >= 1 && amount <= 64) output.stackSize = amount;
                    // Researches from recipes extraction
                    finder.findRecipesPrecise(output);
                } else finder.findRecipesMeta(output);
                chatNull(sender, Arrays.toString(finder.getResearchesFromLastFoundRecipes()));
            } catch (BlockOrItemDoesNotExist e) {
                chat(sender, "§c Item format is incorrect or item doesn't exist ! Please use §csomething like <feather> or <minecraft:feather> or <mod:item:1>");
            } catch (NumberFormatException e) {
                chat(sender, "§c The metadata/amount must be a number !");
            }
        } else throw new WrongUsageException(getCommandUsage(sender));
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        switch (args.length) {
            case 1: return getListOfStringsMatchingLastWord(args, crucible);
            case 2: return getListOfStringsMatchingLastWord(args, output);
            case 3: return getListOfStringsFromIterableMatchingLastWord(args, Item.itemRegistry.getKeys());
        } return null;
    }

    @Override
    public void chatNull(ICommandSender sender, String message) {
        if (!message.equals("null")) {
            chat(sender, "§6 Research found : §r".concat(message));
        } else chat(sender, "§6 No recipes found !");
    }
}
