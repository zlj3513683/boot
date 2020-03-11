package com.example.demo.apo;

import com.example.demo.apo.formatter.Formatter;
import com.example.demo.apo.formatter.StringFormatter;

import java.lang.annotation.*;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value= ElementType.FIELD)
@Documented
public @interface DemoParm {
    String msg() default "not allow null";

    public String formatPattern() default "";

    public Class<? extends Formatter> formatter() default StringFormatter.class;
}
