package com.example.demo.despa.abstractFactory;

import com.example.demo.despa.factory.*;

/**
 * 功能：
 *
 * @author 2020/1/22
 * @author zoulinjun
 */
public class CheFactory extends AbstractFactory {
    @Override
    public Che getChe(String cheTp) {
        if("1".equals(cheTp)){
            return new JiaoChe();
        }else if("2".equals(cheTp)){
            return new KaChe();
        }else if("3".equals(cheTp)){
            return new JipuChe();
        }
        return null;
    }

    @Override
    public Color getColor(String type) {
        return null;
    }
}
