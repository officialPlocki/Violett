package co.plocki.util;

import co.plocki.json.JSONFile;
import co.plocki.json.JSONValue;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.json.JSONObject;

public class MongoDriver {

    private final MongoClient client;
    private final MongoDatabase database;

    public MongoDriver() {

        JSONObject cred = new JSONObject();
        cred.put("database", "database");
        cred.put("host", "localhost:27017");
        cred.put("user", "root");
        cred.put("password", "password");

        JSONObject options = new JSONObject();
        options.put("retryWrites", "true");
        options.put("w", "majority");
        options.put("authSource", "admin");
        options.put("authMechanism", "SCRAM-SHA-1");

        String src = "Alonia/mongodb.json";

        JSONObject finalCred = cred;
        JSONObject finalOptions = options;
        JSONFile file = new JSONFile(src,
                new JSONValue() {
                    @Override
                    public JSONObject object() {
                        return finalCred;
                    }

                    @Override
                    public String objectName() {
                        return "credentials";
                    }
                },
                new JSONValue() {
                    @Override
                    public JSONObject object() {
                        return finalOptions;
                    }

                    @Override
                    public String objectName() {
                        return "options";
                    }
                });
        if(file.isNew()) {
            System.out.println("Please update the mongodb.json file with your credentials.");
            client = null;
            database = null;
            return;
        }

        cred = file.get("credentials");
        options = file.get("options");

        client = MongoClients.create(
                "mongodb://" +
                        cred.getString("user") + ":" +
                        cred.getString("password") + "@" +
                        cred.getString("host") + "/" +
                        cred.getString("database") +
                        "?retryWrites=" + options.getString("retryWrites") +
                        "&w=" + options.getString("w") +
                        "&authSource=" + options.getString("authSource") +
                        "&authMechanism=" + options.getString("authMechanism"));

        database = client.getDatabase(cred.getString("database"));

    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoClient getClient() {
        return client;
    }

    public void close() {
        if(client != null)
            client.close();
    }

}
