package com.frame.test.gp.annotation;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @CREATE 2017/10/15 11:27
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
	boolean required() default true;
}
