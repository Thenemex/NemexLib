package nemexlib.model.commands;

import nemexlib.api.commands.NemexLibCommand;
import nemexlib.api.thaumcraft.API;
import nemexlib.api.util.exceptions.ResearchDoesNotExists;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import thaumcraft.api.research.ResearchItem;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("rawtypes")
public class CommandGetResearchParents extends NemexLibCommand {

    public CommandGetResearchParents() {
        super("get-research-parents", "<RESEARCH-TAG>");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1)
            try {
                ResearchItem research = API.getResearch(args[0]);
                chat(sender, "§6 Parents for ".concat(research.key).concat(" :"));
                // Parents
                if (research.parents != null && research.parents.length != 0)
                    chatNull(sender, "§2 - Parents : §r".concat(Arrays.toString(research.parents)));
                else chat(sender, "§2 - No parents found !");
                // Parents Hidden
                if (research.parentsHidden != null && research.parentsHidden.length != 0)
                    chatNull(sender, "§2 - Parents hidden : §r".concat(Arrays.toString(research.parentsHidden)));
                else chat(sender, "§2 - No hidden parents found !");
            } catch (ResearchDoesNotExists e) {
                chat(sender, "§c This research doesn't exist !");
            }
        else throw new WrongUsageException(getCommandUsage(sender));
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) return getListOfStringsFromIterableMatchingLastWord(args, researchKeys);
        return null;
    }
}
