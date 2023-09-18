package co.plocki.language;

import co.plocki.mongodb.MongoInsert;
import co.plocki.mongodb.MongoRequest;
import co.plocki.mongodb.MongoResponse;
import co.plocki.mongodb.MongoTable;
import co.plocki.util.TranslationUtil;
import org.json.JSONObject;

import java.util.HashMap;

public class Translatable {

    private final TranslationUtil util;
    private final MongoTable.fin translations;
    private final HashMap<String, String> placeholders = new HashMap<>();

    public Translatable() {
        util = new TranslationUtil();
        MongoTable mongoTable = new MongoTable();
        mongoTable.prepare("translations", "original", "de", "en", "fr", "es", "it", "nl", "pl", "ru", "ja");
        translations = mongoTable.build();
    }

    public void setPlaceholder(String placeholder, String value) {
        placeholders.put(placeholder, value);
    }

    public String message(String original, Language target) {
        MongoRequest request = new MongoRequest();
        request.prepare("translations");
        request.addRequirement("original", original);
        MongoResponse response = request.execute();

        if(response == null || response.isEmpty()) {

            HashMap<String, String> values = new HashMap<>();

            for(Language language : Language.values()) {
                JSONObject json = util.translate(original, target.name().toLowerCase());
                if(json.getBoolean("success")) {
                    values.put(language.name(), json.getString("translatedText"));
                } else {
                    String msg = original;
                    for(String placeholder : placeholders.keySet()) {
                        msg = msg.replaceAll(placeholder, placeholders.get(placeholder));
                    }

                    return msg;
                }
            }

            MongoInsert insert = new MongoInsert();
            insert.prepare(translations,
                    original,
                    values.get("DE"),
                    values.get("EN"),
                    values.get("FR"),
                    values.get("ES"),
                    values.get("IT"),
                    values.get("NL"),
                    values.get("PL"),
                    values.get("RU"),
                    values.get("JA"));

            insert.execute();

            String msg = values.get(target.name());
            for(String placeholder : placeholders.keySet()) {
                msg = msg.replaceAll(placeholder, placeholders.get(placeholder));
            }

            return msg;
        }

        String msg = response.getString(target.name());
        for(String placeholder : placeholders.keySet()) {
            msg = msg.replaceAll(placeholder, placeholders.get(placeholder));
        }

        return msg;
    }


}
