package com.example.demo.enums;

import org.springframework.util.StringUtils;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
public enum TransEnum {
    TEXT("1", "transferChannel1"),
    IMAGE("2", "transferChannel2"),
    VIDEO("3", "transferChannel3");

    public final String code;
    public final String name;

    TransEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TransEnum getByCode(String code){
        if(StringUtils.isEmpty(code)){
            return TEXT;
        }
        for (TransEnum errorEnum:values()){
            if(code.equals(errorEnum.code)){
                return errorEnum;
            }
        }
        return TEXT;
    }
}
