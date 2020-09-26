package cf.wellod.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date getCurrDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(new Date());
        try{
            return sdf.parse(s);
        }catch (Exception e){
            System.out.println(e);
            return new Date();
        }
    }
}
