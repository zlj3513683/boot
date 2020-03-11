package com.example.demo.apo;

import com.example.demo.bean.CsInfo;
import com.example.demo.bean.MessageInfo;
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
public class BeanAnnValidImpl implements ConstraintValidator<BeanAnn, Object> {

    @Override
    public boolean isValid(Object msg, ConstraintValidatorContext constraintValidatorContext) {
        if(msg == null){
            return false;
        }
        CsInfo messageInfo = (CsInfo)msg;
        if(messageInfo.getType() == null){
            return false;
        }
        if(messageInfo.getContent() == null || messageInfo.getContent().toString().equals("")){
            return false;
        }
        return true;
    }

    @Override
    public void initialize(BeanAnn constraintAnnotation) {
        System.out.println("初始化会不会进来呢");
    }
}
