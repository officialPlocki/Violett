package world.leafmc.violett.command;

import co.plocki.util.BungeeCordMessenger;
import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LimboCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage(APILoader.prefix + "Connecting to Limbo...");
        BungeeCordMessenger.sendPlayerToServer((Player) sender, "Limbo-1");

        return false;
    }
}
