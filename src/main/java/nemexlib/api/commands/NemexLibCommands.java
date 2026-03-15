package nemexlib.api.commands;

import nemexlib.NemexLib;
import nemexlib.api.items.ItemFinder;
import nemexlib.api.recipes.crucible.CrucibleFinder;
import nemexlib.api.util.exceptions.BlockOrItemDoesNotExist;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import thaumcraft.api.ThaumcraftApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"rawtypes", "SameParameterValue"})
public class NemexLibCommands extends CommandBase {

    protected final CrucibleFinder finder;

    protected static final String help = "help";
    protected static String[] command1;
    protected final ArrayList<String> commands;

    public NemexLibCommands() {
        this.finder = new CrucibleFinder(ThaumcraftApi.getCraftingRecipes());
        this.commands = new ArrayList<>(3);
        this.commands.add(null);
        this.commands.add("find-research-from-recipe");
        command1 = new String[]{"crucible", "output"};
        this.commands.add("get-held-item-NBT");
    }

    @Override public String getCommandName() {
        return NemexLib.modID.toLowerCase();
    }
    @Override public String getCommandUsage(ICommandSender sender) {
        return "/".concat(getCommandName()).concat(" ").concat(help);
    }
    @Override public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0 || args.length == 1 && args[0].equalsIgnoreCase(help)) listAllPrefixes(sender);
            // Command 1 : Find research from recipe
        else if (args[0].equalsIgnoreCase(commands.get(1))) {
            if (args.length >= 4 && args[1].equalsIgnoreCase(command1[0]) && args[2].equalsIgnoreCase(command1[1])) {
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
                    // Researches from recipes extraction
                    if (args.length == 5) finder.findRecipesPrecise(output);
                    else finder.findRecipesMeta(output);
                    chatNull(sender, Arrays.toString(finder.getResearchesFromLastFoundRecipes()), 1);
                } catch (BlockOrItemDoesNotExist e) {
                    chat(sender, "§c Item format is incorrect or item doesn't exist ! Please use §csomething like <feather> or <minecraft:feather> or <mod:item:1>");
                } catch (NumberFormatException e) {
                    chat(sender, "§c The metadata/amount must be a number !");
                }
            } else throw new WrongUsageException(getCommandUsageFull(sender, 1));
        }
        // Command 2 : Get held item NBT
        else if (args[0].equalsIgnoreCase(commands.get(2))) {
            // Item held extraction
            ItemStack heldItem = sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).getHeldItem();
            chatNull(sender, (heldItem == null) ? "null" : heldItem.getTagCompound().toString(), 2);
        }
        else throw new WrongUsageException(getCommandUsage(sender));
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return Collections.emptyList(); // ToDo T_T
    }

    protected void chat(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(message));
    }
    protected void chatNull(ICommandSender sender, String message, int n) {
        switch (n) {
            case 1 :
                sender.addChatMessage(new ChatComponentText("§6".concat(message.equals("null") ? " No recipes found !" : message)));
                break;
            case 2:
                sender.addChatMessage(new ChatComponentText(message.equals("null") ? " §cYou're not holding any item !" : message.equals("{}") ? " §cNo NBT to show !" : "§6".concat(message)));
                break;
        }
    }

    protected void listAllPrefixes(ICommandSender sender) {
        chat(sender, "Usage : /nemexlib <KEYWORD>");
        chat(sender, "Keywords : " + commands.toString());
    }
    protected String getCommandUsageFull(ICommandSender sender, int n) {
        String s = "/nemexlib ";
        // Replace with switch if more commands pop up
        if (n == 1)
            return s + commands.get(1) + " crucible output <ITEM> [AMOUNT]";
        return null;
    }

}
