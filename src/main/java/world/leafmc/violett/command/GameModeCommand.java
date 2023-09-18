package world.leafmc.violett.command;

import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("alonia.command.gamemode")) {
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    if(player.hasPermission("alonia.gamemode.survival")) {
                        player.setGameMode(org.bukkit.GameMode.SURVIVAL);
                        player.sendMessage(APILoader.prefix + "§7Your gamemode has been set to §aSurvival§7.");
                    } else {
                        player.sendMessage(APILoader.noPerm);
                    }
                } else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    if(player.hasPermission("alonia.gamemode.creative")) {
                        player.setGameMode(org.bukkit.GameMode.CREATIVE);
                        player.sendMessage(APILoader.prefix + "§7Your gamemode has been set to §aCreative§7.");
                    } else {
                        player.sendMessage(APILoader.noPerm);
                    }
                } else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    if(player.hasPermission("alonia.gamemode.adventure")) {
                        player.setGameMode(org.bukkit.GameMode.ADVENTURE);
                        player.sendMessage(APILoader.prefix + "§7Your gamemode has been set to §aAdventure§7.");
                    } else {
                        player.sendMessage(APILoader.noPerm);
                    }
                } else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    if(player.hasPermission("alonia.gamemode.spectator")) {
                        player.setGameMode(org.bukkit.GameMode.SPECTATOR);
                        player.sendMessage(APILoader.prefix + "§7Your gamemode has been set to §aSpectator§7.");
                    } else {
                        player.sendMessage(APILoader.noPerm);
                    }
                } else {
                    player.sendMessage(APILoader.prefix + "§cUsage: /gamemode <0|1|2|3|survival|creative|adventure|spectator> [player]");
                }
            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    if(player.hasPermission("alonia.gamemode.survival.other")) {
                        Player target = player.getServer().getPlayer(args[1]);
                        if(target != null) {
                            target.setGameMode(org.bukkit.GameMode.SURVIVAL);
                            player.sendMessage(APILoader.prefix + "§7The gamemode of §a" + target.getName() + " §7has been set to §aSurvival§7.");
                            target.sendMessage(APILoader.prefix + "§7Your gamemode has been set to §aSurvival§7.");
                        } else {
                            player.sendMessage(APILoader.prefix + "§cPlayer not found.");
                        }
                    } else {
                        player.sendMessage(APILoader.noPerm);
                    }
                } else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    if(player.hasPermission("alonia.gamemode.creative.other")) {
                        Player target = player.getServer().getPlayer(args[1]);
                        if(target != null) {
                            target.setGameMode(org.bukkit.GameMode.CREATIVE);
                            player.sendMessage(APILoader.prefix + "§7The gamemode of §a" + target.getName() + " §7has been set to §aCreative§7.");
                            target.sendMessage(APILoader.prefix + "§7Your gamemode has been set to §aCreative§7.");
                        } else {
                            player.sendMessage(APILoader.prefix + "§cPlayer not found.");
                        }
                    } else {
                        player.sendMessage(APILoader.noPerm);
                    }
                } else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    if (player.hasPermission("alonia.gamemode.adventure.other")) {
                        Player target = player.getServer().getPlayer(args[1]);
                        if (target != null) {
                            target.setGameMode(org.bukkit.GameMode.ADVENTURE);
                            player.sendMessage(APILoader.prefix + "§7The gamemode of §a" + target.getName() + " §7has been set to §aAdventure§7.");
                            target.sendMessage(APILoader.prefix + "§7Your gamemode has been set to §aAdventure§7.");
                        } else {
                            player.sendMessage(APILoader.prefix + "§cPlayer not found.");
                        }
                    } else {
                        player.sendMessage(APILoader.noPerm);
                    }
                } else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    if(player.hasPermission("alonia.gamemode.spectator.other")) {
                        Player target = player.getServer().getPlayer(args[1]);
                        if(target != null) {
                            target.setGameMode(org.bukkit.GameMode.SPECTATOR);
                            player.sendMessage(APILoader.prefix + "§7The gamemode of §a" + target.getName() + " §7has been set to §aSpectator§7.");
                            target.sendMessage(APILoader.prefix + "§7Your gamemode has been set to §aSpectator§7.");
                        } else {
                            player.sendMessage(APILoader.prefix + "§cPlayer not found.");
                        }
                    } else {
                        player.sendMessage(APILoader.noPerm);
                    }
                } else {
                    player.sendMessage(APILoader.prefix + "§cUsage: /gamemode <0|1|2|3|survival|creative|adventure|spectator> [player]");
                }
            } else {
                player.sendMessage(APILoader.prefix + "§cUsage: /gamemode <0|1|2|3|survival|creative|adventure|spectator> [player]");
            }
        } else {
            player.sendMessage(APILoader.noPerm);
        }
        return false;
    }
}
