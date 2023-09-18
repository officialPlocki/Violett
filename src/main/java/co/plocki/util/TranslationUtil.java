package co.plocki.util;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TranslationUtil {

    public JSONObject translate(String msg, String targetLanguage) {
        String url = "http://localhost:5000/translate";
        JSONObject json = new JSONObject();
        json.put("q", msg);
        json.put("source", "auto");
        json.put("target", targetLanguage);
        json.put("format", "text");
        json.put("api_key", "");
        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBody = response.body().string();

            responseBody = decodeUnicode(responseBody);

            if(!new JSONObject(responseBody).has("translatedText")) {
                return new JSONObject().put("success", false);
            }

            String translatedText = new JSONObject(responseBody).getString("translatedText");

            return new JSONObject().put("success", true).put("translatedText", translatedText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*String url = "http://localhost:5000/translate";
        Map<String, String> body = new HashMap<>();
        body.put("q", msg);
        body.put("source", "auto");
        body.put("target", targetLanguage);
        body.put("format", "text");
        body.put("api_key", "");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(url);
            request.setHeader("Content-Type", "application/json");
            StringEntity requestBody = new StringEntity(mapToJson(body));
            request.setEntity(requestBody);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity responseEntity = response.getEntity();
                String responseBody = EntityUtils.toString(responseEntity);

                if(!new JSONObject(responseBody).has("translatedText")) {
                    return new JSONObject().put("success", false);
                }

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                String translatedText = URLDecoder.decode(jsonNode.get("translatedText").asText(), StandardCharsets.UTF_8);

                return new JSONObject().put("success", true).put("translatedText", translatedText);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject().put("success", false);*/
    }

    private String decodeUnicode(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, StandardCharsets.UTF_8);
    }

    private String mapToJson(Map<String, String> map) {
        StringBuilder builder = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!first) {
                builder.append(",");
            } else {
                first = false;
            }
            builder.append("\"").append(entry.getKey()).append("\":\"")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8).replaceAll("\\+", "%20"))
                    .append("\"");
        }
        builder.append("}");
        return builder.toString();
    }

}
