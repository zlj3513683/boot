package com.example.demo.despa.abstractFactory;

import com.example.demo.despa.factory.Che;
import com.example.demo.despa.factory.Color;

/**
 * 功能：
 *
 * @author 2020/1/22
 * @author zoulinjun
 */
public abstract  class AbstractFactory {
    public abstract  Che getChe(String type);
    public abstract Color getColor(String type);
}
