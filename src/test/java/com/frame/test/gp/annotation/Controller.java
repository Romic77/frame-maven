package com.frame.test.gp.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @CREATE 2017/10/15 11:27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
	String value() default "";
}
