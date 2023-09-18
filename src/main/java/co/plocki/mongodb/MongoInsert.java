package co.plocki.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import world.leafmc.violett.Violett;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoInsert {

    private String collection;
    private List<String> columns;
    private List<String> values;

    /**
     * It creates a statement that will insert a row into a table if the row doesn't already exist
     *
     * @param information This is the enum that contains the table name and the values that are required.
     */
    public void prepare(MongoTable.fin information, Object... values) {
        collection = information.getTableName();
        columns = Arrays.asList(information.getValues());
        this.values = new ArrayList<>();
        for (Object value : values) {
            this.values.add(String.valueOf(value));
        }
    }

    public void prepare(MongoTable.fin information, List<Object> values) {
        collection = information.getTableName();
        columns = Arrays.asList(information.getValues());
        this.values = new ArrayList<>();
        for (Object value : values) {
            this.values.add(String.valueOf(value));
        }
    }

    /**
     * It executes a SQL statement.
     */
    public void execute() {
        MongoCollection<Document> coll = new Violett().getAPI().getMongoDriver().getDatabase().getCollection(collection);
        Document document = new Document();
        for (int i = 0; i < columns.size(); i++) {
            document.append(columns.get(i), values.get(i));
        }
        coll.insertOne(document);
    }

}
