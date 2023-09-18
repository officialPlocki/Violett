package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHereCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("alonia.command.tphere")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        target.teleport(player.getLocation());
                        player.sendMessage(APILoader.prefix + "§e" + target.getName() + " §7has been teleported to you.");
                    } else {
                        player.sendMessage(APILoader.prefix + "Player not found.");
                    }
                } else {
                    player.sendMessage(APILoader.prefix + "§cUsage: /tphere <player>");
                }
            } else {
                sender.sendMessage(APILoader.noPerm);
            }
        }
        return true;
    }

}
