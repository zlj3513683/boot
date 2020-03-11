package com.example.demo.apo;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 功能：
 *
 * @author 2020/1/17
 * @author zoulinjun
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BeanAnnValidImpl.class)
public @interface BeanAnn {

    String message() default "存在不合法的属性";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
