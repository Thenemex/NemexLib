package nemexlib.api.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class NemexLibCommand extends CommandBase {

    private static final String prefix = "nemexlib-";
    protected static final String help = "help";
    private final String commandName;

    public NemexLibCommand(String commandName) {
        this.commandName = prefix.concat(commandName);
    }

    @Override public String getCommandName() {
        return commandName;
    }
    @Override public String getCommandUsage(ICommandSender sender) {
        // ToDo How to add an helping message telling "this command does this blabla"
        return "/".concat(getCommandName()).concat(" ").concat(help);
    }
    @Override public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override public abstract void processCommand(ICommandSender sender, String[] args);

    @Override public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return super.addTabCompletionOptions(sender, args);
    }

    protected String getCommandUsageFull() {
        return "/".concat(getCommandName());
    }

    protected void chat(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(message));
    }
    protected void chatNull(ICommandSender sender, String message) {
        chat(sender, message == null ? "null" : message);
    }
}
