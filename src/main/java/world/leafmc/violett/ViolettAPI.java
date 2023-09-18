package world.leafmc.violett;

import co.plocki.json.JSONFile;
import co.plocki.util.MongoDriver;
import co.plocki.util.MySQLDriver;

public class ViolettAPI {

    private MySQLDriver driver;
    private MongoDriver mongoDriver;
    private JSONFile config;

    private final String prefix = "§aʟᴇᴀꜰᴍᴄ §8» §7";

    public void initialize() {
        driver = new MySQLDriver();
        mongoDriver = new MongoDriver();
        config = new JSONFile("Violett/config.json");
    }

    public void deflate() {
        driver.close();
        mongoDriver.close();
    }

    public MySQLDriver getMySQLDriver() {
        return driver;
    }

    public MongoDriver getMongoDriver() {
        return mongoDriver;
    }

    public JSONFile getConfig() {
        return config;
    }

}
