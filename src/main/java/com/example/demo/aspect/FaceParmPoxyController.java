package com.example.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.exception.AppBizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 功能：
 *
 * @author 2019/12/30
 * @author zoulinjun
 */
@Slf4j
@Aspect
@Component
public class FaceParmPoxyController {

    @Pointcut("target(com.example.demo.FaceParmController)")
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
            log.error("自定义异常出现：",e);
            return responseBySystemErr(e.getCode(),e.getMessage());
        }catch (Exception e){
            log.error("未知异常出现:",e);
            return responseBySystemErr("9999","未知异常,请联系开发人员");
        }
    }


    private JSONObject responseBySystemErr(String retCode, String retMsg) {
        JSONObject posFaceResponse = new JSONObject();
        posFaceResponse.put("retCode",retCode);
        posFaceResponse.put("retMsg",retMsg);
        return posFaceResponse;
    }


}
