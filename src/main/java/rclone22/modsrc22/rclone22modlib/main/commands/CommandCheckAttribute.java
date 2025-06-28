package rclone22.modsrc22.rclone22modlib.main.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import rclone22.modsrc22.rclone22modlib.api.attributes.AbsResEntityAttributes;

public class CommandCheckAttribute extends CommandBase {


    @Override
    public String getName() {
        return "checkattrresistance";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/checkattrresistance [player]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer target;

        if (args.length == 0) {

            if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
                target = (EntityPlayer) sender.getCommandSenderEntity();
            } else {
                sender.sendMessage(new TextComponentString("You must specify a player!"));
                return;
            }
        } else {

            target = server.getPlayerList().getPlayerByUsername(args[0]);
            if (target == null) {
                sender.sendMessage(new TextComponentString("Player not found!"));
                return;
            }
        }


        double value = AbsResEntityAttributes.getAbsResAttrResistance(target);


        sender.sendMessage(new TextComponentString(
                target.getName() + "'s Absolute Resistance attribute value is: " + value));

    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}