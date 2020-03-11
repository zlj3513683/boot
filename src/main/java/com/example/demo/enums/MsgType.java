package com.example.demo.enums;

/**
 * 功能：
 *
 * @author 2020/1/13
 * @author zoulinjun
 */
public enum  MsgType {
    TEXT(1, "文本"),
    IMAGE(2, "图片"),
    VIDEO(3, "视频");

    public final int code;
    public final String name;

    MsgType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
