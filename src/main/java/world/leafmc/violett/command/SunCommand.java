package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SunCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if(player.hasPermission("alonia.command.sun")) {
            player.getWorld().setWeatherDuration(0);
            player.sendMessage(APILoader.prefix + "The sun is now shining!");
        } else
            player.sendMessage(APILoader.noPerm);

        return false;
    }
}
