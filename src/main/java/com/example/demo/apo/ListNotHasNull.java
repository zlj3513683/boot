package com.example.demo.apo;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 功能：
 *
 * @author 2020/1/17
 * @author zoulinjun
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ANNOTATION_TYPE, METHOD, FIELD})
@Constraint(validatedBy = ListNotHasNullValidatorImpl.class)
public @interface ListNotHasNull {

    /**
     * 添加value属性，可以作为校验时的条件,若不需要，可去掉此处定义
     */
    int value() default 0;

    String message() default "List集合中不能含有null元素";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 定义List，为了让Bean的一个属性上可以添加多套规则
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ListNotHasNull[] value();
    }

}
