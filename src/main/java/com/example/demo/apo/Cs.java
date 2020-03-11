package com.example.demo.apo;

import java.lang.annotation.*;

/**
 * 功能：自定义注解demo
 *
 * @author 2020/1/17
 * @author zoulinjun
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cs {
}
