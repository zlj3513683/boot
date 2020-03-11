package com.example.demo.apo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 功能：
 *
 * @author 2020/1/17
 * @author zoulinjun
 */
@Service
@Slf4j
public class ListNotHasNullValidatorImpl implements ConstraintValidator<ListNotHasNull, List> {

    private int value;


    @Override
    public void initialize(ListNotHasNull constraintAnnotation) {
        log.info("-------------------------------------初始化-----------------------");
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();

    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        if(list == null ){
            return false;
        }
        for (Object object : list) {
            if (object == null) {
                //如果List集合中含有Null元素，校验失败
                return false;
            }
        }
        return true;

    }
}
