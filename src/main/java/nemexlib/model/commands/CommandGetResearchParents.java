package nemexlib.model.commands;

import nemexlib.api.commands.NemexLibCommand;
import nemexlib.api.thaumcraft.API;
import nemexlib.api.util.comparators.ResearchComparator;
import nemexlib.api.util.exceptions.ResearchDoesNotExists;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import thaumcraft.api.research.ResearchItem;

import java.util.*;

import static thaumcraft.api.research.ResearchCategories.getCategoryName;
import static thaumcraft.common.lib.research.ResearchManager.*;

@SuppressWarnings("rawtypes")
public class CommandGetResearchParents extends NemexLibCommand {

    private static final String missing = "missing";

    public CommandGetResearchParents() {
        super("get-research-parents", "<RESEARCH-TAG> [MISSING]");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        ResearchItem research = null;
        // Getting the research, if it exists
        if (args.length >= 1) {
            try {
                research = API.getResearch(args[0]);
            } catch (ResearchDoesNotExists e) {
                chat(sender, "§c This research doesn't exist !");
                return;
            }
        }
        if (research == null) return;
        // Printing parents from the research
        if (args.length == 1) {
            chat(sender, "§6 Parents for ".concat(research.key).concat(" :"));
            // Parents
            if (research.parents != null && research.parents.length != 0)
                chat(sender, "§2 - Parents : §r".concat(Arrays.toString(research.parents)));
            else chat(sender, "§2 - No parents found !");
            // Parents Hidden
            if (research.parentsHidden != null && research.parentsHidden.length != 0)
                chat(sender, "§2 - Parents hidden : §r".concat(Arrays.toString(research.parentsHidden)));
            else chat(sender, "§2 - No hidden parents found !");
        }
        // Printing parents missing from the player
        else if (args.length == 2 && args[1].equals(missing)) {
            ArrayList<ResearchItem> list = getMissingParents(sender.getCommandSenderName(), research);
            // You missed some research
            if (!list.isEmpty()) {
                chat(sender, "§6 Researchs you're missing :");
                for (ResearchItem res : list)
                    chat(sender, getMessage(res));
            }
            // The research doesn't have any parent
            else if ((research.parents == null || research.parents.length == 0) && (research.parentsHidden == null || research.parentsHidden.length == 0)) {
                chat(sender, "§6 This research doesn't have any parent !");
            }
            // The player already research all parents
            else chat(sender, "§6 You already researched all the parents !");
        }
        else throw new WrongUsageException(getCommandUsage(sender));
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        switch (args.length) {
            case 1 : return getListOfStringsFromIterableMatchingLastWord(args, researchKeys);
            case 2 : return getListOfStringsMatchingLastWord(args, missing);
        } return null;
    }

    private ArrayList<ResearchItem> getMissingParents(String playerName, ResearchItem research) {
        ArrayList<ResearchItem> list = new ArrayList<>();
        for (String parent : research.parents)
            if (!getResearchForPlayerSafe(playerName).contains(parent) && !list.contains(API.getResearch(parent)))
                list.add(API.getResearch(parent));
        for (String parent : research.parentsHidden)
            if (!getResearchForPlayerSafe(playerName).contains(parent) && !list.contains(API.getResearch(parent)))
                list.add(API.getResearch(parent));
        list.sort(new ResearchComparator());
        return list;
    }

    private String getMessage(ResearchItem research) {
        return "§2 [".concat(getCategoryName(research.category)).concat("] : §r").concat(research.getName());
    }
}
