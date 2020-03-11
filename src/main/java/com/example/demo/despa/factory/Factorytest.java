package com.example.demo.despa.factory;

/**
 * 功能：
 *
 * @author 2020/1/22
 * @author zoulinjun
 */
public class Factorytest {
    public static void main(String[] args) {
        String[] cheArr = {"1","3","2"};
        for (String cheTp:
             cheArr) {
            Che che = CheFactory.getChe(cheTp);
            che.print();
        }
    }
}
