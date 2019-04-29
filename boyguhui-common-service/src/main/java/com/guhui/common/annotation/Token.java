package com.guhui.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 注解Token代码
 * @author guhui
 * @version Revision:v1.0
 */
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Token {
	boolean isToken() default false;

	boolean isEntToken() default false;
}