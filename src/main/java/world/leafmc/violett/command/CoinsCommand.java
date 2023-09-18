package world.leafmc.violett.command;

import co.plocki.language.Translatable;
import eu.alonia.util.APILoader;
import eu.alonia.util.economy.CoinsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand extends Translatable implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        /*
        AloniaBukkitPlayer aloniaPlayer = new AloniaBukkitPlayer(player.getUniqueId().toString());

        this.setPlaceholder("%c", new DecimalFormat("#,###").format(new CoinsAPI(player).getCoins()));

        player.sendMessage(APILoader.prefix + message("Your coins: §a%c", aloniaPlayer.getLanguage()));

         */

        player.sendMessage(APILoader.prefix + "Your coins: §a" + new CoinsAPI(player).getCoins());
        return true;
    }

}
