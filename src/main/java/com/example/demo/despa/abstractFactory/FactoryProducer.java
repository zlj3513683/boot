package com.example.demo.despa.abstractFactory;

/**
 * 功能：
 *
 * @author 2020/1/22
 * @author zoulinjun
 */
public class FactoryProducer {
    public static AbstractFactory getAbstaractFactory(String choice){
        if(choice.equalsIgnoreCase("CHE")){
            return new CheFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
