package bot.utils;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

/**
 * this class implements some function for getting a variable by channel owner
 */

public class GetSystemEnvironmentOrDefaultValue {

    private static Map<String, String> defaultValueMap = new HashMap<>();

    static {
        defaultValueMap.put("BOT_USER_ID", "null-17893234757432");
        }

    public static String get(String key) {
        try {
            String value = System.getenv(key);
            if (value != null) return value;
            throw new InvalidKeyException();
        }
        catch (Exception e) {
            return defaultValueMap.get(key);
        }
    }
}
