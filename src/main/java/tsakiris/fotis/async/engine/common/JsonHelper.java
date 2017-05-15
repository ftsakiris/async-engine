package tsakiris.fotis.async.engine.common;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonHelper {

    private static Gson gson = new Gson();

    public static String convert(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T convert(String jsonStr, Class<T> clazz) {
        return gson.fromJson(jsonStr, clazz);
    }

    public static Object convert(String jsonStr, Type listType) {
        return gson.fromJson(jsonStr, listType);
    }
}
