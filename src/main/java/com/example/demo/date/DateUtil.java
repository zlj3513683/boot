package com.example.demo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/4/15
 */
public class DateUtil {

    public static void main(String[] args) {
        String otime = "20200414164244";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Date dt= null;
        try {
            dt = sdf.parse(otime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar newTime = Calendar.getInstance();
        newTime.setTime(dt);
        newTime.add(Calendar.SECOND,7200);//日期加10秒

        Date dt1=newTime.getTime();
        String retval = sdf.format(dt1);
        System.out.println(retval);
    }

}
