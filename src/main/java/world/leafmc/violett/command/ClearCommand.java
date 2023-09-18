package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("alonia.command.clear")) {
            if (args.length != 1) {
                sender.sendMessage("§cUsage: /clear <player>");
                return true;
            } else {
                Objects.requireNonNull(sender.getServer().getPlayer(args[0])).getInventory().clear();
                sender.sendMessage("§aPlayer inventory has been cleared!");
            }
        } else {
            sender.sendMessage(APILoader.noPerm);
        }

        return false;
    }
}
