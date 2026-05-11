package nemexlib.api.commands;

import net.minecraft.command.ICommandSender;

public interface INemexLibCommand {

    void chat(ICommandSender sender, String message);
    void chatNull(ICommandSender sender, String message);
}
