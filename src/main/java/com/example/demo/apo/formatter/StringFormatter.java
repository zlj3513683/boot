package com.example.demo.apo.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
public class StringFormatter extends Formatter {
    public StringFormatter(String var1) {
        super(var1);
    }

    public StringFormatter() {
        super("StringFormatter");
    }
    @Override
    public String format(Object var1) {
        return var1 == null ? null : ((String)var1).trim();
    }
    @Override
    public Object unformat(String var1) {
        return var1 == null ? null : var1.trim();
    }
    @Override
    public void setPattern(String var1) {
    }

    public static Date toTime(String var0, String var1) throws ParseException {
        SimpleDateFormat var2 = new SimpleDateFormat("HH" + var1 + "mm" + var1 + "ss");
        return var2.parse(var0);
    }

    public static Date toDate(String var0, String var1) throws ParseException {
        SimpleDateFormat var2 = new SimpleDateFormat("yyyy" + var1 + "MM" + var1 + "dd");
        return var2.parse(var0);
    }

    public static Date toDateTime(String var0, String var1, String var2, String var3) throws ParseException {
        SimpleDateFormat var4 = new SimpleDateFormat("yyyy" + var1 + "MM" + var1 + "dd" + var2 + "HH" + var3 + "mm" + var3 + "ss");
        return var4.parse(var0);
    }
}
