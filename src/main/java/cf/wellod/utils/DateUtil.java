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


    // 获取今天所在年份(2020)
    public static String getCurrDateYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String s = sdf.format(new Date());
        return s;
    }

    // 获取今天所在月份(202001)
    public static String getCurrDateMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String s = sdf.format(new Date());
        return s;
    }

    // 获取今天所在天(20200101)
    public static String getCurrDateDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String s = sdf.format(new Date());
        return s;
    }
}
