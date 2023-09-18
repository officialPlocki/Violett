package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if(player.hasPermission("alonia.command.night")) {
            player.getWorld().setTime(13000);
            player.sendMessage(APILoader.prefix + "The time has been set to night.");
        } else {
            player.sendMessage(APILoader.noPerm);
        }

        return false;
    }
}
