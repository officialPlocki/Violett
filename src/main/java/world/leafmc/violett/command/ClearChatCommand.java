package world.leafmc.violett.command;

import co.plocki.language.Translatable;
import eu.alonia.util.APILoader;
import eu.alonia.util.custom.AloniaSound;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand extends Translatable implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        /*

        this.setPlaceholder("%p", sender.getName());

        if(sender.hasPermission("alonia.command.clearchat")) {
            for(int i = 0; i < 400; i++) {
                Bukkit.broadcastMessage("");
            }
            for(Player player : Bukkit.getOnlinePlayers()) {
                AloniaBukkitPlayer aloniaPlayer = new AloniaBukkitPlayer(player.getUniqueId().toString());
                player.sendMessage(APILoader.prefix + message("Chat has been cleared by §e%p", aloniaPlayer.getLanguage()));
            }
        } else {
            sender.sendMessage(APILoader.noPerm);
        }

         */

        if(sender.hasPermission("alonia.command.clearchat")) {
            for(int i = 0; i < 400; i++) {
                Bukkit.broadcastMessage("");
            }
            Bukkit.broadcastMessage(APILoader.prefix + "§aChat has been cleared by §e" + sender.getName());

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), new AloniaSound().getSoundString(AloniaSound.Sound.UI_CRYSTAL), 1, 1);
            }
        } else {
            sender.sendMessage(APILoader.noPerm);
        }
        return false;
    }

}
