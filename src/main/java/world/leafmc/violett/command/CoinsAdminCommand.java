package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import eu.alonia.util.economy.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;

public class CoinsAdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("alonia.command.coinsadmin")) {
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("view")) {
                    if(args[1].startsWith("uuid:")) {
                        String uuid = args[1].replace("uuid:", "");
                        CoinsAPI cubitAPI = new CoinsAPI(uuid);
                        sender.sendMessage(APILoader.prefix + "§7Coins of §a" + uuid + "§7: §a" + new DecimalFormat("#,###").format(cubitAPI.getCoins()));
                    } else {
                        CoinsAPI cubitAPI = new CoinsAPI(Bukkit.getOfflinePlayer(args[1]));
                        sender.sendMessage(APILoader.prefix + "§7Coins of §a" + args[1].toUpperCase() + "§7: §a" + new DecimalFormat("#,###").format(cubitAPI.getCoins()));
                    }
                } else {
                    sender.sendMessage(APILoader.prefix + "§cUsage: /ca <add, remove, view> <player name / uuid:PLAYER_UUID> [amount]");
                }
            } else if(args.length == 3) {
                if(args[0].equalsIgnoreCase("add")) {
                    if(args[1].startsWith("uuid:")) {
                        String uuid = args[1].replace("uuid:", "");
                        CoinsAPI cubitAPI = new CoinsAPI(uuid);
                        cubitAPI.addCoins(Double.parseDouble(args[2]));
                        sender.sendMessage(APILoader.prefix + "§7Coins of §a" + uuid + "§7: §a" + new DecimalFormat("#,###").format(cubitAPI.getCoins()) + " §7(§a+" + new DecimalFormat("#,###").format(Double.parseDouble(args[2])) + "§7)");
                    } else {
                        CoinsAPI cubitAPI = new CoinsAPI(Bukkit.getOfflinePlayer(args[1]));
                        cubitAPI.addCoins(Double.parseDouble(args[2]));
                        sender.sendMessage(APILoader.prefix + "§7Coins of §a" + args[1].toUpperCase() + "§7: §a" + new DecimalFormat("#,###").format(cubitAPI.getCoins()) + " §7(§a+" + new DecimalFormat("#,###").format(Double.parseDouble(args[2])) + "§7)");
                    }
                } else if(args[0].equalsIgnoreCase("remove")) {
                    if(args[1].startsWith("uuid:")) {
                        String uuid = args[1].replace("uuid:", "");
                        CoinsAPI cubitAPI = new CoinsAPI(uuid);
                        cubitAPI.removeCoins(Double.parseDouble(args[2]));
                        sender.sendMessage(APILoader.prefix + "§7Coins of §a" + uuid + "§7: §a" + new DecimalFormat("#,###").format(cubitAPI.getCoins()) + " §7(§c-" + new DecimalFormat("#,###").format(Double.parseDouble(args[2])) + "§7)");
                    } else {
                        CoinsAPI cubitAPI = new CoinsAPI(Bukkit.getOfflinePlayer(args[1]));
                        cubitAPI.removeCoins(Double.parseDouble(args[2]));
                        sender.sendMessage(APILoader.prefix + "§7Coins of §a" + args[1] + "§7: §a" + new DecimalFormat("#,###").format(cubitAPI.getCoins()) + " §7(§c-" + new DecimalFormat("#,###").format(Double.parseDouble(args[2])) + "§7)");
                    }
                } else if(args[0].equalsIgnoreCase("set")) {
                    if(args[1].startsWith("uuid:")) {
                        String uuid = args[1].replace("uuid:", "");
                        CoinsAPI cubitAPI = new CoinsAPI(uuid);
                        cubitAPI.setCoins(Double.parseDouble(args[2]));
                        sender.sendMessage(APILoader.prefix + "§7Coins of §a" + uuid + "§7: §a" + new DecimalFormat("#,###").format(cubitAPI.getCoins()));
                    } else {
                        CoinsAPI cubitAPI = new CoinsAPI(Bukkit.getOfflinePlayer(args[1]));
                        cubitAPI.setCoins(Double.parseDouble(args[2]));
                        sender.sendMessage(APILoader.prefix + "§7Coins of §a" + args[1] + "§7: §a" + new DecimalFormat("#,###").format(cubitAPI.getCoins()));
                    }
                } else {
                    sender.sendMessage(APILoader.prefix + "§cUsage: /ca <add, remove, view, set> <player name / uuid:PLAYER_UUID> [amount]");
                }
            } else {
                sender.sendMessage(APILoader.prefix + "§cUsage: /ca <add, remove, view, set> <player name / uuid:PLAYER_UUID> [amount]");
            }
        } else {
            sender.sendMessage(APILoader.noPerm);
        }
        return false;
    }

}
