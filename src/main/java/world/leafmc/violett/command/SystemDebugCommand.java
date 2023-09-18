package world.leafmc.violett.command;

import co.plocki.mongodb.*;
import co.plocki.mysql.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.management.ManagementFactory;
import java.util.List;

public class SystemDebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender.hasPermission("alonia.command.systemdebug")) {

            sender.sendMessage(APILoader.bluePrefix + "§aSystem Debug \n");

            //check

            boolean mysqlOnline = !APILoader.mySQLDriver.getHikariDataSource().isClosed();
            long tableMS = 0;
            long insertMS = 0;
            long selectMS = 0;
            long deleteMS = 0;
            long updateMS = 0;

            if(mysqlOnline) {
                long temp = System.currentTimeMillis();
                MySQLTable table = new MySQLTable();
                table.prepare("test_table", "random1", "random2");
                MySQLTable.fin test_table = table.build();

                tableMS = System.currentTimeMillis() - temp;
                temp = System.currentTimeMillis();

                MySQLInsert insert = new MySQLInsert();
                insert.prepare(test_table, "test", true);
                insert.execute();

                insertMS = System.currentTimeMillis() - temp;
                temp = System.currentTimeMillis();

                MySQLRequest request = new MySQLRequest();
                request.prepare("random1", "test_table");
                request.addRequirement("random2", true);
                request.execute();

                selectMS = System.currentTimeMillis() - temp;
                temp = System.currentTimeMillis();

                MySQLPush push = new MySQLPush();
                push.prepare("test_table", "random2", false);
                push.addRequirement("random1", "test");
                push.execute();

                updateMS = System.currentTimeMillis() - temp;
                temp = System.currentTimeMillis();

                MySQLDelete delete = new MySQLDelete();
                delete.prepare("test_table");
                delete.addRequirement("random2", false);
                delete.execute();

                deleteMS = System.currentTimeMillis() - temp;
            }

            sender.sendMessage("§aMySQL Online: " + mysqlOnline);
            sender.sendMessage("§aMySQL Table: " + tableMS + "ms (" + (tableMS / 1000) + "s)");
            sender.sendMessage("§aMySQL Insert: " + insertMS + "ms (" + (insertMS / 1000) + "s)");
            sender.sendMessage("§aMySQL Select: " + selectMS + "ms (" + (selectMS / 1000) + "s)");
            sender.sendMessage("§aMySQL Update: " + updateMS + "ms (" + (updateMS / 1000) + "s)");
            sender.sendMessage("§aMySQL Delete: " + deleteMS + "ms (" + (deleteMS / 1000) + "s)");

            long freeMB = Runtime.getRuntime().freeMemory() / 1024 / 1024;
            long usedMB = Runtime.getRuntime().totalMemory() / 1024 / 1024;
            long maxMB = Runtime.getRuntime().maxMemory() / 1024 / 1024;
            long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
            long uptime = System.currentTimeMillis() - startTime;
            long uptimeSeconds = uptime / 1000;
            long uptimeMinutes = uptimeSeconds / 60;
            long uptimeHours = uptimeMinutes / 60;
            long uptimeDays = uptimeHours / 24;
            uptimeSeconds %= 60;
            uptimeMinutes %= 60;
            uptimeHours %= 24;
            double cpuUsage = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
            double availableProcessors = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();

            long mongoTableMS = System.currentTimeMillis();

            MongoTable table = new MongoTable();
            table.prepare("test_table", "random1", "random2");
            MongoTable.fin test_table = table.build();

            mongoTableMS = System.currentTimeMillis() - mongoTableMS;

            sender.sendMessage("§aMongoDB Table: " + mongoTableMS + "ms (" + (mongoTableMS / 1000) + "s)");

            long mongoInsertMS = System.currentTimeMillis();

            MongoInsert insert = new MongoInsert();
            insert.prepare(test_table, "test", true);
            insert.execute();

            mongoInsertMS = System.currentTimeMillis() - mongoInsertMS;

            sender.sendMessage("§aMongoDB Insert: " + mongoInsertMS + "ms (" + (mongoInsertMS / 1000) + "s)");

            long mongoSelectMS = System.currentTimeMillis();

            MongoRequest request = new MongoRequest();
            request.prepare("test_table");
            request.addRequirement("random2", true);
            request.execute();

            mongoSelectMS = System.currentTimeMillis() - mongoSelectMS;

            sender.sendMessage("§aMongoDB Select: " + mongoSelectMS + "ms (" + (mongoSelectMS / 1000) + "s)");

            long mongoUpdateMS = System.currentTimeMillis();

            MongoPush push = new MongoPush();
            push.prepare("test_table", "random2", false);
            push.addRequirement("random1", "test");
            push.execute();

            mongoUpdateMS = System.currentTimeMillis() - mongoUpdateMS;

            sender.sendMessage("§aMongoDB Update: " + mongoUpdateMS + "ms (" + (mongoUpdateMS / 1000) + "s)");

            long mongoDeleteMS = System.currentTimeMillis();

            MongoDelete delete = new MongoDelete();
            delete.prepare("test_table");
            delete.addRequirement("random2", false);
            delete.execute();

            mongoDeleteMS = System.currentTimeMillis() - mongoDeleteMS;

            sender.sendMessage("§aMongoDB Delete: " + mongoDeleteMS + "ms (" + (mongoDeleteMS / 1000) + "s)");

            long systemLag = System.currentTimeMillis();

            System.out.println("Testing lag...");

            systemLag = System.currentTimeMillis() - systemLag;


            sender.sendMessage("§aFree RAM: " + freeMB + "MB");
            sender.sendMessage("§aUsed RAM: " + usedMB + "MB");
            sender.sendMessage("§aMax RAM: " + maxMB + "MB");
            sender.sendMessage("§aUptime: " + uptimeDays + "d " + uptimeHours + "h " + uptimeMinutes + "m " + uptimeSeconds + "s");
            sender.sendMessage("§aCPU Usage: " + cpuUsage + "%");
            sender.sendMessage("§aAvailable Processors: " + availableProcessors);
            sender.sendMessage("§aSystem Lag: " + systemLag + "ms (" + (systemLag / 1000) + "s)");


            int players = Bukkit.getServer().getOnlinePlayers().size();
            int maxPlayers = Bukkit.getServer().getMaxPlayers();

            sender.sendMessage("§aPlayers: " + players + "/" + maxPlayers);
            sender.sendMessage("\n" + APILoader.bluePrefix + "§aEnd of System Debug");
        } else {
            sender.sendMessage(APILoader.blueNoPerm);
        }

        return false;
    }
}
