package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 功能：注解demo的实现
 *
 * @author 2020/1/17
 * @author zoulinjun
 */
@Component
@Slf4j
@Aspect
public class CsAspectImpl {
    @Pointcut("@annotation(com.example.demo.apo.Cs)")
    private void cut(){
        log.info("3");
    }

    /**
     * 环绕切点,在进入切点前,跟切点后执行
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
        log.info("1");
         try{
                    joinPoint.proceed();
             Object obj = joinPoint.proceed(joinPoint.getArgs());
             return obj;
         }catch (Exception e){
                    e.printStackTrace();
        
         }
        log.info("4");
         return "1111";
     }

    /**
     * 在切点前执行方法,内容为指定的切点
     */
    @Before("cut()")
    public void before(){
        log.info("2");
     }

    /**
     * 在切点后,return前执行,
     */
    @After("cut()")
    public void after(){
        log.info("5");
     }

}
