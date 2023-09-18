package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage(APILoader.prefix + "If you want to report a player, please join our discord server and open a support ticket.\n\n§r https://discord.gg/xMkqcEcPKp");

        return false;
    }
}
