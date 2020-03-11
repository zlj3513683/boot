package com.example.demo.handle;

import com.example.demo.enums.MsgType;

import java.lang.annotation.*;

/**
 * 功能：
 *
 * @author 2020/1/13
 * @author zoulinjun
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MsgTypeHandler {
    MsgType value();
}
