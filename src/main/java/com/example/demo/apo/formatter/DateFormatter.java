package com.example.demo.apo.formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
public class DateFormatter extends Formatter {

    private static final String[] patterns = new String[]{"yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yy-MM-dd", "yy/MM/dd", "yyMMdd"};
    private String pattern = "yyyy/MM/dd";
    protected static Logger logger = LoggerFactory.getLogger(DateFormatter.class);

    public DateFormatter(String var1) {
        super(var1);
    }

    public DateFormatter() {
        super("DateFormatter");
    }

    public String format(Object var1) {
        if (var1 == null) {
            return null;
        } else {
            try {
                SimpleDateFormat var2 = new SimpleDateFormat(this.pattern);
                if (var1 instanceof Number) {
                    var1 = new Date(((Number)var1).longValue());
                } else if (var1 instanceof java.sql.Date) {
                    var1 = new Date(((java.sql.Date)var1).getTime());
                }

                if (!(var1 instanceof Date) && !(var1 instanceof Calendar)) {
                    String var3 = var1.toString();
                    var1 = this.unformat(var1.toString());
                    return var1 == null ? var3 : var2.format(var1);
                } else {
                    return var2.format(var1);
                }
            } catch (Throwable var4) {
                throw new RuntimeException("格式化器异常", var4);
            }
        }
    }

    @Override
    public Object unformat(String var1) {
        if (var1 != null && !var1.trim().equals("")) {
            SimpleDateFormat var2 = new SimpleDateFormat(this.pattern);

            try {
                return var2.parse(var1);
            } catch (Exception var6) {
                if (logger.isDebugEnabled()) {
                    logger.debug("FORMAT_ERROR", var6);
                }

                int var3 = 0;

                while(var3 < patterns.length) {
                    var2.applyPattern(patterns[var3]);

                    try {
                        return var2.parse(var1);
                    } catch (Exception var5) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("FORMAT_ERROR", var5);
                        }

                        ++var3;
                    }
                }

                return null;
            }
        } else {
            return null;
        }
    }

    public String getPattern() {
        return this.pattern;
    }
    @Override
    public void setPattern(String var1) {
        this.pattern = var1;
    }

    public static String format(Date var0, String var1) {
        SimpleDateFormat var2 = new SimpleDateFormat(var1);
        return var2.format(var0);
    }

    public static Date unformat(String var0, String var1) {
        if (var0 != null && !"".equals(var0.trim())) {
            SimpleDateFormat var2 = new SimpleDateFormat(var1);

            try {
                return var2.parse(var0);
            } catch (Exception var6) {
                int var3 = 0;

                while(var3 < patterns.length) {
                    var2.applyPattern(patterns[var3]);

                    try {
                        return var2.parse(var0);
                    } catch (Exception var5) {
                        ++var3;
                    }
                }

                return null;
            }
        } else {
            return null;
        }
    }

}
