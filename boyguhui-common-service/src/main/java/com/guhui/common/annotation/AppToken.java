package com.guhui.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 注解AppToken代码
 * @author guhui
 * @version Revision:v1.0
 */
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AppToken {
	
	/** true：保证会员是登录状态才能进入业务处理 */
	boolean isToken() default true;
}