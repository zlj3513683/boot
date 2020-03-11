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
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Constraint(validatedBy = MyNotNullValitor.class)
public @interface MyNotNull {

    String message() default "not null";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };


}
