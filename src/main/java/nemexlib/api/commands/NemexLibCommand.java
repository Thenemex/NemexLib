package nemexlib.api.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class NemexLibCommand extends CommandBase {

    private static final String prefix = "nemexlib-";
    private final String commandName, prefixes;

    public NemexLibCommand(String commandName) {
        this(commandName, "");
    }
    public NemexLibCommand(String commandName, String prefixes) {
        this.commandName = commandName;
        this.prefixes = prefixes;
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
}
