package nemexlib.api.commands;

import nemexlib.NemexLib;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public abstract class NemexLibCommand extends CommandBase {

    protected final StringBuilder sb;

    public NemexLibCommand() {
        this(10);
    }
    public NemexLibCommand(int sbSize) {
        this.sb = new StringBuilder(sbSize);
    }

    @Override
    public String getCommandName() {
        return NemexLib.modID.toLowerCase();
    }

    protected void chat(ICommandSender sender, String message) {
        sender.addChatMessage(new ChatComponentText(message));
    }
}
