package com.example.demo.aspect;

import com.example.demo.apo.Cs;
import com.example.demo.exception.AppBizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 功能：
 *
 * @author 2019/12/30
 * @author zoulinjun
 */
@Slf4j
@Aspect
@Component
public class DemoPoxyController {

    @Pointcut("target(com.example.demo.DemoController)")
    public void demoServicePointCut(){}


    /**
     * 环绕通知
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("demoServicePointCut()")
    public Object processXpospService(ProceedingJoinPoint jp) throws Throwable {
        try{
            Object obj = jp.proceed(jp.getArgs());
            return obj;
        }catch (AppBizException e){
            log.error("xposp service failed!",e);
            return e.getCode()+"||||" + e.getMessage();
        }catch (Exception e){
            log.error("系统异常",e);
            return "0000 "+"未知错误";
        }
    }


}
