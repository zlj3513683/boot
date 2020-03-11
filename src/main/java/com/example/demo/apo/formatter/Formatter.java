package com.example.demo.apo.formatter;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
public abstract class Formatter {
    String name;

    public Formatter(String var1) {
        this.name = var1;
    }

    public Formatter() {
        this("");
    }

    public String getName() {
        return this.name;
    }

    public abstract String format(Object var1);

    public abstract Object unformat(String var1);

    public abstract void setPattern(String var1);
}
