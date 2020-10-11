package cf.wellod.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    public static Map<String,Object> getFiledInfo(Object o) {
        Map<String,Object> parameters = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            parameters.put(field.getName(), getFieldValueByName(field.getName(), o));
        }
        return parameters;
    }

    public static  Object getFieldValueByName(String fieldName,Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

}


