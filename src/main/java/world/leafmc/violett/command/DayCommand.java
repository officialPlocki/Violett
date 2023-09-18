package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if(player.hasPermission("alonia.command.day")) {
            player.getWorld().setTime(1000);
            player.sendMessage(APILoader.prefix + "The time was set to day.");
        } else {
            player.sendMessage(APILoader.noPerm);
        }

        return false;
    }
}
