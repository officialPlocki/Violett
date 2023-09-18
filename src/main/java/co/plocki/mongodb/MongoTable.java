package co.plocki.mongodb;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import world.leafmc.violett.Violett;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MongoTable {

    private String collection;
    private List<String> values;

    /**
     * It creates a table if it doesn't exist, and if it does, it doesn't do anything
     *
     * @param tableName The name of the table you want to create.
     */
    public void prepare(String tableName, String... values) {
        this.values = Arrays.stream(values).collect(Collectors.toList());
        this.collection = tableName;
    }

    public void prepare(String tableName, List<String> values) {
        this.values = values;
        this.collection = tableName;
    }

    /**
     * It executes the statement and returns an object that contains the table name and values
     *
     * @return An anonymous class that implements the fin interface.
     */
    public fin build() {
        MongoDatabase coll = new Violett().getAPI().getMongoDriver().getDatabase();
        if(!coll.listCollectionNames().into(new ArrayList<>()).contains(collection)) {
            coll.createCollection(collection, new CreateCollectionOptions().capped(false));
        }
        return new fin() {
            @Override
            public String getTableName() {
                return collection;
            }

            @Override
            public String[] getValues() {
                return values.toArray(new String[values.size()]);
            }
        };
    }

    public interface fin {

        /**
         * Returns the name of the table
         *
         * @return The name of the table.
         */
        String getTableName();

        /**
         * Returns an array of all the values in the map
         *
         * @return An array of strings.
         */
        String[] getValues();

    }

}
