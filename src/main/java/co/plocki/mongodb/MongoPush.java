package co.plocki.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import world.leafmc.violett.Violett;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MongoPush {

    private String collection;
    private String key;
    private String value;
    private final HashMap<String, String> requirements;

    public MongoPush() {
        this.requirements = new HashMap<>();
    }

    /**
     * Prepare an update statement.
     *
     * @param table The table you want to update
     * @param param The parameter you want to update.
     * @param value The value that it should be changed to
     */
    public void prepare(String table, String param, Object value) {
        collection = table;
        key = param;
        this.value = String.valueOf(value);
    }

    /**
     * It adds a requirement to the push
     *
     * @param value       The value to be pushed to the database.
     * @param requirement The requirement to be added.
     */
    public void addRequirement(String value, Object requirement) {
        requirements.put(value, requirement.toString());
    }

    /**
     * It takes the statement and the requirements and combines them into one statement
     */
    public void execute() {
        MongoCollection<Document> coll = new Violett().getAPI().getMongoDriver().getDatabase().getCollection(collection);

        if(requirements.isEmpty()) {
            coll.updateMany(Filters.empty(), new Document("$set", new Document(key, value)));
        } else {
            List<Bson> filters = new ArrayList<>();

            requirements.forEach((s, s2) -> {
                filters.add(Filters.eq(s, s2));
            });

            coll.updateMany(Filters.and(filters), new Document("$set", new Document(key, value)));
        }
    }

}
