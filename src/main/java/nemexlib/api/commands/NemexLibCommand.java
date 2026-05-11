package nemexlib.api.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;

import java.util.ArrayList;
import java.util.List;

import static thaumcraft.api.research.ResearchCategories.researchCategories;

@SuppressWarnings({"rawtypes"})
public abstract class NemexLibCommand extends CommandBase implements INemexLibCommand {

    protected static ArrayList<String> researchKeys;
    private static final String prefix = "nemexlib-";
    private final String commandName, suffixes;

    public NemexLibCommand(String commandName) {
        this(commandName, "");
    }
    public NemexLibCommand(String commandName, String suffixes) {
        this.commandName = prefix.concat(commandName);
        this.suffixes = suffixes;
        initResearchKeys();
    }

    @Override public String getCommandName() {
        return commandName;
    }
    @Override public String getCommandUsage(ICommandSender sender) {
        return "/".concat(getCommandName()).concat(" ").concat(suffixes);
    }
    @Override public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override public abstract void processCommand(ICommandSender sender, String[] args);

    @Override public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return super.addTabCompletionOptions(sender, args);
    }

    @Override public void chat(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(message));
    }
    @Override public void chatNull(ICommandSender sender, String message) {
        chat(sender, message == null ? "null" : message);
    }

    private void initResearchKeys() {
        if (researchKeys != null) return;
        // Finding perfect size for the list
        int cpt = 0;
        for (ResearchCategoryList rl : researchCategories.values())
            for (ResearchItem ri : rl.research.values())
                cpt++;
        // Instanciating and filling the list
        researchKeys = new ArrayList<>(cpt);
        for (ResearchCategoryList rl : researchCategories.values())
            for (ResearchItem ri : rl.research.values())
                researchKeys.add(ri.key);
    }
}
