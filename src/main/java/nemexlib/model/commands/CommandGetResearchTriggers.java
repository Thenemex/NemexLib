package nemexlib.model.commands;

import nemexlib.api.commands.NemexLibCommand;
import nemexlib.api.thaumcraft.API;
import nemexlib.api.util.exceptions.ResearchDoesNotExists;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchItem;

import java.util.List;

@SuppressWarnings("rawtypes")
public class CommandGetResearchTriggers extends NemexLibCommand {

    private int call;
    private ResearchItem research;

    public CommandGetResearchTriggers() {
        super("get-research-triggers", "<RESEARCH-TAG>");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1)
            try {
                this.research = API.getResearch(args[0]);
                chat(sender, "§6 Triggers for ".concat(research.key));
                for (this.call = 1; call <= 3; call++)
                    chatNull(sender, getMessage());
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

    @Override
    public void chatNull(ICommandSender sender, String message) {
        if (message.equals("null") || message.equals("[]")) message = "";
        message = "§2 - ".concat(getTrigger()).concat(" : §r").concat(message);
        chat(sender, message);
    }

    private String getMessage() {
        StringBuilder sb = new StringBuilder(20).append("[");
        Object[] triggers = null;
        try {
            switch (call) {
                case 1: triggers = research.getItemTriggers(); break;
                case 2: triggers = research.getEntityTriggers(); break;
                case 3: triggers = research.getAspectTriggers(); break;
            }
        } catch (NullPointerException e) {
            return "";
        }
        if (triggers == null || triggers.length == 0) return "";
        for (int i = 0; i < triggers.length; i++) {
            switch (call) {
                case 1:
                    if (triggers[i] instanceof ItemStack)
                        sb.append(((ItemStack) triggers[i]).getDisplayName());
                    break;
                case 2 :
                    if (triggers[i] instanceof String)
                        if (((String) triggers[i]).contains("Thaumcraft."))
                            sb.append(((String) triggers[i]).split("Thaumcraft.")[1]);
                        else sb.append(((String) triggers[i]));
                case 3 :
                    if (triggers[i] instanceof Aspect)
                        sb.append(WordUtils.capitalize(((Aspect) triggers[i]).getTag()));
                    break;
                }
            sb.append(i == triggers.length - 1 ? "]" : ", ");
        }
        return sb.toString();
    }
    private String getTrigger() {
        switch (call) {
            case 1 : return "Items";
            case 2 : return "Entities";
            case 3 : return "Aspects";
        } return "";
    }
}
