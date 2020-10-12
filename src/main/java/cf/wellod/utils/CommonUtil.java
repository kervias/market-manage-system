package cf.wellod.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonUtil {
    public static Map<String,Object> getFiledInfo(Object o) {
        Map<String,Object> parameters = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            parameters.put(field.getName(), getFieldValueByName(field.getName(), o));
        }
        return parameters;
    }

    public static Object getFieldValueByName(String fieldName,Object o) {
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

    // 通过生产批次生成商品ID
    public static String generateGoodsIdByBatch(String batch){
        String uuid = UUID.randomUUID().toString().substring(0,4);
        return batch + uuid;
    }

    //通过日期转换为生产批次
    public static String generateGoodsBatchByProdDate(Date prodDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateString = simpleDateFormat.format(prodDate);
        return dateString;
    }
}


