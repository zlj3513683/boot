package com.example.demo.util;

import com.example.demo.exception.AppBizException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 功能：
 *
 * @author 2020/1/19
 * @author zoulinjun
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws AppBizException  校验不通过，则报Exception异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws AppBizException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new AppBizException("0000",msg.toString());
        }
    }
}
