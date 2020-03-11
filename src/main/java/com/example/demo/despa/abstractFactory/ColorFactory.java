package com.example.demo.despa.abstractFactory;

import com.example.demo.despa.factory.*;

/**
 * 功能：
 *
 * @author 2020/1/22
 * @author zoulinjun
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Che getChe(String type) {
        return null;
    }

    @Override
    public Color getColor(String type) {
        if("1".equals(type)){
            return new Black();
        }else if("2".equals(type)){
            return new Blue();
        }else if("3".equals(type)){
            return new Green();
        }
        return null;
    }
}
