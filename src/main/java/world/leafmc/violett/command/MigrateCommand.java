package world.leafmc.violett.command;

import co.plocki.mongodb.MongoInsert;
import co.plocki.mongodb.MongoTable;
import co.plocki.mysql.MySQLRequest;
import co.plocki.mysql.MySQLResponse;
import eu.alonia.util.APILoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class MigrateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender.hasPermission("alonia.admin.migrate")) {
            if(args.length == 1) {
                String table = args[0];

                new Thread(() -> {
                    MySQLRequest request = new MySQLRequest();
                    request.prepare("*", table);
                    MySQLResponse response = request.execute();

                    for (HashMap<String, String> stringStringHashMap : response.rawAll()) {
                        MongoTable mongoTable = new MongoTable();
                        mongoTable.prepare(table, new ArrayList<>(stringStringHashMap.keySet()));
                        MongoTable.fin fin = mongoTable.build();

                        MongoInsert insert = new MongoInsert();
                        insert.prepare(fin, new ArrayList<>(stringStringHashMap.values()));
                        insert.execute();

                        sender.sendMessage("§aMigrating §e" + table + "§a... (" + String.join(", ", stringStringHashMap.values()) + ")");
                    }

                    sender.sendMessage("§aSuccessfully migrated §e" + table + "§a!");
                }).start();
            }
        } else {
            sender.sendMessage(APILoader.blueNoPerm);
        }

        return false;
    }
}
