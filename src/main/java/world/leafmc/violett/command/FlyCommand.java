package world.leafmc.violett.command;

import co.plocki.language.Translatable;
import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends Translatable implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        /*

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("alonia.command.fly")) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.sendMessage(APILoader.prefix + message("You are no longer in flight mode.", new AloniaBukkitPlayer(player.getUniqueId().toString()).getLanguage()));
                } else {
                    player.setAllowFlight(true);
                    player.sendMessage(APILoader.prefix + message("You are now in flight mode.", new AloniaBukkitPlayer(player.getUniqueId().toString()).getLanguage()));
                }
            } else {
                player.sendMessage(APILoader.noPerm);
            }
        } else {
            commandSender.sendMessage(APILoader.prefix + "§cYou must be a player to do this.");
        }

         */

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("alonia.command.fly")) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.sendMessage(APILoader.prefix + "You are no longer in flight mode.");
                } else {
                    player.setAllowFlight(true);
                    player.sendMessage(APILoader.prefix + "You are now in flight mode.");
                }
            } else {
                player.sendMessage(APILoader.noPerm);
            }
        } else {
            commandSender.sendMessage(APILoader.prefix + "§cYou must be a player to do this.");
        }
        return false;
    }

}
