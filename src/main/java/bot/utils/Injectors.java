package bot.utils;


import java.lang.reflect.Field;

// reflection from field
public class Injectors {

    public static void injectionFieldEnvValue(Object instance){
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(SystemEnvironment.class)) {
                SystemEnvironment set = field.getAnnotation(SystemEnvironment.class);
                field.setAccessible(true); // should work on private fields!!!
                try {
                    field.set(instance, System.getenv(set.value()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
