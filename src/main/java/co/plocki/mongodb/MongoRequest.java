package co.plocki.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import world.leafmc.violett.Violett;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MongoRequest {

    private String collection;
    private final HashMap<String, String> requirements;

    public MongoRequest() {
        requirements = new HashMap<>();
    }

    /**
     * Prepare a SELECT statement.
     *
     * @param table  The table you want to select from
     */
    public void prepare(String table) {
        collection = table;
    }

    public void prepare(String easyMigrationFeatureThisWillBeIgnored, String table) {
        collection = table;
    }

    /**
     * It adds a requirement to the request
     *
     * @param value       The column name
     * @param requirement The value of the requirement.
     */
    public void addRequirement(String value, Object requirement) {
        requirements.put(value, requirement.toString());
    }

    /**
     * It executes the statement and returns the result
     *
     * @return A MongoResponse object.
     */
    public MongoResponse execute() {

        MongoCollection<Document> collection = new Violett().getAPI().getMongoDriver().getDatabase().getCollection(this.collection);


        if(!requirements.isEmpty())  {
            List<Bson> filters = new ArrayList<>();

            requirements.forEach((s, s2) -> {
                filters.add(Filters.eq(s, s2));
            });

            FindIterable<Document> documents = collection.find(Filters.and(filters));

            List<Document> docs = new ArrayList<>();

            for (Document document : documents) {
                docs.add(document);
            }

            if(docs.size() > 1) {
                return new MongoResponse(docs);
            } else if(docs.size() == 0) {
                return new MongoResponse();
            } else {
                return new MongoResponse(Objects.requireNonNull(documents.first()));
            }
        } else {
            FindIterable<Document> documents = collection.find(Filters.empty());

            List<Document> docs = new ArrayList<>();

            for (Document document : documents) {
                docs.add(document);
            }

            if(docs.size() > 1) {
                return new MongoResponse(docs);
            } else if(docs.size() == 0) {
                return new MongoResponse();
            } else {
                return new MongoResponse(Objects.requireNonNull(documents.first()));
            }
        }


    }

}
