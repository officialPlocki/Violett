package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if(player.hasPermission("alonia.command.rain")) {
            player.getWorld().setClearWeatherDuration(0);
            player.getWorld().setStorm(true);
            player.getWorld().setThundering(true);
            player.sendMessage(APILoader.prefix + "Rain has been enabled!");
        } else {
            player.sendMessage(APILoader.noPerm);
        }

        return false;
    }
}
