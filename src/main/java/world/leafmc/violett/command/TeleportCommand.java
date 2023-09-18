package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    /**
     * The onCommand function is called when a player executes the command.
     *
     *
     * @param sender Determine who sent the command
     * @param command Get the command that was used
     * @param label Get the command label
     * @param args Get the arguments that are passed to the command
     *
     * @return A boolean, which is used to determine whether or not the command was successful
     *
     * @docauthor Trelent
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("alonia.command.tp")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        player.teleport(target.getLocation());
                        player.sendMessage(APILoader.prefix + "You were teleported to §e" + target.getName() + "§7.");
                    } else {
                        player.sendMessage(APILoader.prefix + "Player not found.");
                    }
                } else {
                    player.sendMessage(APILoader.prefix + "§cUsage: /tp <player>");
                }
            } else {
                sender.sendMessage(APILoader.noPerm);
            }
        }

        return false;
    }

}
