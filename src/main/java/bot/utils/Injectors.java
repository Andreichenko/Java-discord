package bot.utils;


import java.lang.reflect.Field;

// reflection from field
public class Injectors {

    public static void injectionFieldEnvValue(Object instance){
        Field[] fields = instance.getClass().getDeclaredFields();

    }
}
