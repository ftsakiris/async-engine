package tsakiris.fotis.async.engine.common;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {

    private static final int DEFAULT_SUPPRESS_MAX_LENGTH = 14;

    /**
     * Checks whether an object is empty, in a null safe manner Checks cases of
     * String, Collection, Map, Array objects
     *
     * @param value The object to check
     * @return <tt>true</tt> if the object is empty or <tt>null</tt>
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return (((String) value).trim().length() == 0);
        } else if (value instanceof Collection) {
            return ((Collection<?>) value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map<?, ?>) value).isEmpty();
        } else {
            return value.getClass().isArray() && (Array.getLength(value) == 0);
        }
    }

    public static boolean hasText(String s) {
        return (null != s) && !s.trim().isEmpty();
    }

    public static Double safeDouble(String string, Double defaultVal) {
        try {
            return Double.valueOf(string);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static int safeInt(String string, int defaultVal) {
        try {
            return Integer.valueOf(string);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static boolean safeBoolean(String string, boolean defaultVal) {
        try {
            return Boolean.valueOf(string);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static String suppressText(String text) {
        if (text == null) {
            return text;
        }
        if (text.length() > DEFAULT_SUPPRESS_MAX_LENGTH) {
            return text.substring(0, DEFAULT_SUPPRESS_MAX_LENGTH) + "..";
        } else {
            return text;
        }
    }

    public static String fixTextWidthRight(String text, int maxLength) {
        if (text == null) {
            return text;
        }
        if (text.length() > maxLength) {
            return text.substring(0, maxLength) + "..";
        } else {
            String newStr = "";
            int size = maxLength - text.length();
            if (size <= 0) {
                return text.substring(0, text.length() + size);
            } else {
                for (int i = 0; i < size; i++) {
                    newStr += " ";
                }
               return newStr + text;
            }
        }
    }

    public static String fixTextWidthLeft(String text, Integer maxLength) {
        return fixTextWidthLeft(text, maxLength, " ");
    }

    public static String fixTextWidthLeft(String text, Integer maxLength, String appendText) {
        if (text == null || maxLength == null) {
            return text;
        }
        if (text.length() > maxLength) {
            return text.substring(0, maxLength) + "..";
        } else {
            String newStr = "";
            int size = maxLength - text.length();
            if (size <= 0) {
                return text.substring(0, text.length() + size);
            } else {
                for (int i = 0; i < size; i++) {
                    newStr += appendText;
                }
                return text + newStr;
            }
        }
    }

    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    public static <T> T getFirstElement(List<T> collection) {
        return collection.iterator().next();
    }

    public synchronized static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static void createPath(String directory) {
        Path path = Paths.get(directory);
        try {
            Files.createDirectories(path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
