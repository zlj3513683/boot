package com.example.demo.Service;

import com.example.demo.exception.AppBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

/**
 * 功能：
 *
 * @author 2019/12/30
 * @author zoulinjun
 */
@Slf4j
@Service
public class DemoServiceImpl  implements DemoService{

    @Override
    public void test() {
//        Annotation
        int i = 99;
        if(i < 100){
            log.error("报错了");
            throw new AppBizException("1234","小于100");
        }
    }
}
