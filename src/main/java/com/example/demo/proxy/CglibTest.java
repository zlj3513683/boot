package com.example.demo.proxy;

/**
 * @author zoulinjun
 * @title: CglibTest
 * @projectName boot
 * @description: TODO
 * @date 2021/1/4 11:48
 */
public class CglibTest {

    public static void main(String[] args) {
        House house = new HouseImpl();
        CglibInterceptor cglibInterceptor = new CglibInterceptor();
        House houseProxy = (House)cglibInterceptor.getInstance(house);
        houseProxy.buyHouse();
    }

}
