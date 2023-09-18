package world.leafmc.violett.command;

import eu.alonia.core.spigot.SpigotLoader;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

public class PluginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("alonia.command.plugin")) {
            for(Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if(plugin.isEnabled()) {
                    sender.sendMessage("§a" + plugin.getName() + " §7- §e" + plugin.getDescription().getVersion());
                } else {
                    sender.sendMessage("§c" + plugin.getName() + " §7- §e" + plugin.getDescription().getVersion());
                }
            }
        } else {
            ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) itemStack.getItemMeta();
            assert meta != null;
            meta.setAuthor("Alonia");
            meta.setTitle("Plugin list");
            meta.setPages(SpigotLoader.bookPages);
            itemStack.setItemMeta(meta);
            ((Player) sender).openBook(itemStack);
        }
        return false;
    }

}
