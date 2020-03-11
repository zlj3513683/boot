package com.example.demo.apo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 功能：
 *
 * @author 2020/1/17
 * @author zoulinjun
 */
@Slf4j
@Service
public class MyNotNullValitor implements ConstraintValidator<MyNotNull,Object> {


    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null || o.toString().equals("")){
            return false;
        }
        return true;
    }

    @Override
    public void initialize(MyNotNull constraintAnnotation) {
        log.info("自定义数据验证处理类的初始化...");
    }
}
