package nemexlib.api.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

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

    protected String getCommandUsageFull(ICommandSender sender) {
        return getCommandName();
    }

    protected void chat(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(message));
    }
}
