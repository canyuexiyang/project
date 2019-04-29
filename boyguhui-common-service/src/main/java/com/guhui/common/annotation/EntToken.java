package com.guhui.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 注解EntToken代码
 * @author guhui
 * @version Revision:v1.0
 */
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EntToken {
	/** true：企业ID被验证通过才能进入业务处理 */
	boolean isToken() default false;

	/** true：对请求进行权限拦截 */
	boolean isAuth() default true;
}