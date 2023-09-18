package co.plocki.mongodb;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MongoResponse {

    private final List<HashMap<String, String>> values;
    private final HashMap<String, String> value;
    private final boolean noll;

    public MongoResponse(Document document) {
        HashMap<String, String> v1 = new HashMap<>();

        document.forEach((key, value) -> {
            String val = String.valueOf(value);
            v1.put(key, val);

        });

        values = Collections.singletonList(v1);
        value = v1;
        noll = false;
    }

    public MongoResponse(List<Document> documents) {
        List<HashMap<String, String>> list = new ArrayList<>();

        for (Document document : documents) {
            HashMap<String, String> v1 = new HashMap<>();

            document.forEach((key, value) -> {

                String val = String.valueOf(value);

                v1.put(key, val);

            });

            list.add(v1);
        }

        values = list;
        value = list.get(0);
        noll = false;
    }

    public MongoResponse() {
        this.values = null;
        this.value = null;
        noll = true;
    }

    public String getString(String column) {
        return value.get(column);
    }

    public double getDouble(String column) {
        return Double.parseDouble(value.get(column));
    }

    public int getInt(String column) {
        return Integer.parseInt(value.get(column));
    }

    public long getLong(String column) {
        return Long.parseLong(value.get(column));
    }

    public float getFloat(String column) {
        return Float.parseFloat(value.get(column));
    }

    public boolean getBoolean(String column) {
        return Boolean.parseBoolean(value.get(column));
    }

    public boolean isEmpty() {
        return value == null || values == null || values.isEmpty() && value.isEmpty() || noll;
    }

    public Object get(String column) {
        return value.get(column);
    }

    public HashMap<String, String> rawFirst() {
        return value;
    }

    public List<HashMap<String, String>> rawAll() {
        return values;
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
