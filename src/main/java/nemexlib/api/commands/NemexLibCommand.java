package nemexlib.api.commands;

import nemexlib.NemexLib;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class NemexLibCommand extends CommandBase {

    protected final StringBuilder sb;
    protected int permission;

    public NemexLibCommand() {
        this(10);
    }
    public NemexLibCommand(int sbSize) {
        this(sbSize, 0);
    }
    public NemexLibCommand(int sbSize, int permission) {
        this.sb = new StringBuilder(sbSize);
        this.permission = permission;
    }

    @Override
    public String getCommandName() {
        return NemexLib.modID.toLowerCase();
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return sb.toString();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return permission;
    }

    protected void chat(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(message));
    }

    public abstract void processCommand(ICommandSender sender, String[] args);
    public abstract List addTabCompletionOptions(ICommandSender sender, String[] args);
    protected abstract void chatNull(ICommandSender sender, String message);
}
