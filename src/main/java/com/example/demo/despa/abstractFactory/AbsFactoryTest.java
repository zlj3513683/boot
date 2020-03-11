package com.example.demo.despa.abstractFactory;


import com.example.demo.despa.factory.Che;
import com.example.demo.despa.factory.Color;

/**
 * 功能：
 *
 * @author 2020/1/22
 * @author zoulinjun
 */
public class AbsFactoryTest {
    public static void main(String[] args) {
        String[] facArr = {"CHE","COLOR"};
        for (String fac:
                facArr) {
            AbstractFactory abstaractFactory = FactoryProducer.getAbstaractFactory(fac);
            if(abstaractFactory instanceof CheFactory ){
                String[] cheArr = {"1","3","2"};
                for (String cheTp:
                        cheArr) {
                    Che che = abstaractFactory.getChe(cheTp);
                    che.print();
                }
            }
            if(abstaractFactory instanceof ColorFactory ){
                String[] cheArr = {"1","3","2"};
                for (String cheTp:
                        cheArr) {
                    Color color = abstaractFactory.getColor(cheTp);
                    color.drow();
                }
            }

        }
    }
}
