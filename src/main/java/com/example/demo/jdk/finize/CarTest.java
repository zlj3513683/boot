package com.example.demo.jdk.finize;

import java.lang.ref.WeakReference;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/7
 */
public class CarTest {
    public static void main(String[] args) {
        Car car = new Car(9999, "black");
        WeakReference<Car> carWeakReference = new WeakReference<Car>(car);

        int i = 0;
        while(true) {
            if(carWeakReference.get() != null) {
                i++;
                System.out.println("Object is alive for "+i+" loops - "+carWeakReference);
            } else {
                System.out.println("Object has been collected.");
                break;
            }
        }

    }
}
