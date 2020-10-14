package cf.wellod.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    // 添加月份
    public static Date getDateAfterAddMonth(Date date, Integer month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }
}
