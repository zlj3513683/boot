package com.example.demo.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zoulinjun
 * @title: CglibInterceptor
 * @projectName boot
 * @description: TODO
 * @date 2021/1/4 11:45
 */
public class CglibInterceptor implements MethodInterceptor {

    private Object target;
    public Object getInstance(final Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("start.........");
        Object res = methodProxy.invokeSuper(o,objects);
        System.out.println("end.........");
        return res;
    }
}
