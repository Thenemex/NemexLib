package nemexlib.model.commands;

import nemexlib.api.commands.NemexLibCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;

public class CommandGetHeldItemNBT extends NemexLibCommand {

    public CommandGetHeldItemNBT() {
        super("get-held-item-NBT");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        // Item held extraction
        ItemStack heldItem = sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).getHeldItem();
        chatNull(sender, (heldItem == null) ? "null" : (heldItem.getTagCompound() == null) ? "{}" : (heldItem.getTagCompound().toString() == null) ? "{}" : heldItem.getTagCompound().toString());
    }

    @Override
    public void chatNull(ICommandSender sender, String message) {
        chat(sender, message.equals("null") ? " §cYou're not holding any item !" : (message.equals("{}")) ? " §cNo NBT to show !" : "§6 NBT : §r".concat(message));
    }
}
