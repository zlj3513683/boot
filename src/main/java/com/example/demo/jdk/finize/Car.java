package com.example.demo.jdk.finize;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/7
 */
class Car {
    private double price;
    private String colour;

    public Car(double price, String colour){
        this.price = price;
        this.colour = colour;
    }

    // get set method

    @Override
    protected void finalize() throws Throwable {
        System.out.println("i will be destroyed");
    }
}
