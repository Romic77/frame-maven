package com.frame.test.gp.annotation;


import java.lang.annotation.*;

/**
 * @author Administrator
 * @CREATE 2017/10/15 11:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
	String value() default "";
}
