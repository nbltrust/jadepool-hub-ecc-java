package com.jadepool.ecc;

import com.google.gson.*;
import org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Utils {

    public static String byteArrayToHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for(byte a: data)
            sb.append(String.format("%02x", a));
        return sb.toString();
    }

    public static String byteArrayToBase64(byte[] data) {
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    public static String base64ToHex(String data) {
        return byteArrayToHex(Base64.decodeBase64(data));
    }

    public static boolean isBase64 (String data) {
        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
        try {
            decoder.decode(data);
            return true;
        } catch(IllegalArgumentException ex) {
            return false;
        }
    }

    static String preprocess(String result, Long timestamp) {
        String res = null;
        try {
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            JsonParser p = new JsonParser();
            JsonObject inputObj  = g.fromJson(result, JsonObject.class);
            inputObj.addProperty("timestamp", timestamp.toString());
            String newStr = g.toJson(inputObj);
            JsonElement e = p.parse(newStr);
            sort(e);
            res = toStr(e);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }

    static void sort(JsonElement e) {
        if (e.isJsonNull())
        {
            return;
        }

        if (e.isJsonPrimitive())
        {
            return;
        }

        if (e.isJsonArray())
        {
            JsonArray a = e.getAsJsonArray();
            for (Iterator<JsonElement> it = a.iterator(); it.hasNext();)
            {
                sort(it.next());
            }
            return;
        }

        if (e.isJsonObject())
        {
            Map<String, JsonElement> tm = new TreeMap<String, JsonElement>(getComparator());
            for (Map.Entry<String, JsonElement> en : e.getAsJsonObject().entrySet())
            {
                tm.put(en.getKey(), en.getValue());
            }

            for (Map.Entry<String, JsonElement> en : tm.entrySet())
            {
                e.getAsJsonObject().remove(en.getKey());
                e.getAsJsonObject().add(en.getKey(), en.getValue());
                sort(en.getValue());
            }
            return;
        }
    }

    static Comparator<String> getComparator() {
        Comparator<String> c = new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        return c;
    }

    static String toStr(JsonElement e){

        StringBuilder sb = new StringBuilder();
        if (e.isJsonArray()) {
            JsonArray a = e.getAsJsonArray();
            int counter = 0;
            for (int i = 0; i < a.size(); i++) {
                sb.append(counter);
                sb.append(toStr(a.get(i)));
                counter++;
            }
        } else if (e.isJsonPrimitive()) {
            if (e.getAsJsonPrimitive().isNumber()) {
                BigDecimal bd = new BigDecimal(e.toString());
                sb.append(bd.toString());
            } else {
                sb.append(e.getAsString());
            }

        } else if (e.isJsonObject()) {
            Map<String, JsonElement> tm = new TreeMap<String, JsonElement>(getComparator());
            for (Map.Entry<String, JsonElement> en : e.getAsJsonObject().entrySet())
            {
                tm.put(en.getKey(), en.getValue());
            }

            for (Map.Entry<String, JsonElement> en : tm.entrySet())
            {
                sb.append(en.getKey());
                sb.append(toStr(en.getValue()));
            }
        } else {
            sb.append(e.getAsString());
        }

        return sb.toString();
    }
}
