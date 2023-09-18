package world.leafmc.violett.command;

import co.plocki.language.Translatable;
import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand extends Translatable implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        /*

        if(sender.hasPermission("alonia.command.feed")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setFoodLevel(20);
                player.sendMessage(APILoader.prefix + message("You have been fed.", new AloniaBukkitPlayer(player.getUniqueId().toString()).getLanguage()));
            } else {
                sender.sendMessage(APILoader.prefix + "§cYou must be a player to execute this command.");
            }
        } else {
            sender.sendMessage(APILoader.noPerm);
        }

         */

        if(sender.hasPermission("alonia.command.feed")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setFoodLevel(20);
                player.sendMessage(APILoader.prefix + "You have been fed.");
            } else {
                sender.sendMessage(APILoader.prefix + "§cYou must be a player to execute this command.");
            }
        } else {
            sender.sendMessage(APILoader.noPerm);
        }

        return false;
    }

}
