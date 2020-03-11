package com.example.demo.util;

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
public class BeanUtil {
    /**
     * 验证某个bean的参数
     *
     * @param object 被校验的参数
     * @throws ValidationException 如果参数校验不成功则抛出此异常
     */
//    public static <T> void validate(T object) {
//        //获得验证器
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        //执行验证
//        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
//        //如果有验证信息，则将第一个取出来包装成异常返回
//        ConstraintViolation<T> constraintViolation = getFirst(constraintViolations, null);
//        if (constraintViolation != null) {
//            System.out.println(constraintViolation.getPropertyPath());
//            System.out.println(constraintViolation.getConstraintDescriptor().getAnnotation().annotationType());
//            System.out.println(constraintViolation.getMessage());
//        }
//    }
}
