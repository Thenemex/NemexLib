package nemexlib.api.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"rawtypes", "NullableProblems"})
public abstract class NemexLibCommand extends CommandBase implements Iterable<String> {

    private static ArrayList<String> researchKeys;
    private static final String prefix = "nemexlib-";
    private final String commandName, prefixes;

    public NemexLibCommand(String commandName) {
        this(commandName, "");
    }
    public NemexLibCommand(String commandName, String prefixes) {
        this.commandName = commandName;
        this.prefixes = prefixes;
        initResearchKeys();
    }

    @Override public String getCommandName() {
        return commandName;
    }
    @Override public String getCommandUsage(ICommandSender sender) {
        return "/".concat(getCommandName()).concat(" ").concat(prefixes);
    }
    @Override public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override public abstract void processCommand(ICommandSender sender, String[] args);

    @Override public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return super.addTabCompletionOptions(sender, args);
    }

    protected void chat(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(message));
    }
    protected void chatNull(ICommandSender sender, String message) {
        chat(sender, message == null ? "null" : message);
    }

    @Override
    public Iterator<String> iterator() {
        return researchKeys.iterator();
    }

    private void initResearchKeys() {
        if (researchKeys != null) return;
        // Finding perfect size for the list
        int cpt = 0;
        for (ResearchCategoryList rl : ResearchCategories.researchCategories.values())
            for (ResearchItem ri : rl.research.values())
                cpt++;
        // Instanciating and filling the list
        researchKeys = new ArrayList<>(cpt);
        for (ResearchCategoryList rl : ResearchCategories.researchCategories.values())
            for (ResearchItem ri : rl.research.values())
                researchKeys.add(ri.key);
    }
}
