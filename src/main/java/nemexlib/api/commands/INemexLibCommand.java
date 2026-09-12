package nemexlib.api.commands;

import net.minecraft.command.ICommandSender;

public interface INemexLibCommand {

    void chat(final ICommandSender sender, final String message);
    void chatNull(final ICommandSender sender, final String message);
}
