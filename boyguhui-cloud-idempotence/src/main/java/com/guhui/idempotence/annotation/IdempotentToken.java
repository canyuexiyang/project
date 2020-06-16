package com.guhui.idempotence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 保证接口幂等性，在方法Controller上使用此注解。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IdempotentToken {

	/**
	 * value值为true代表开启注解，需要验证。false放行，不验证
	 * @return
	 */
	boolean value() default false;

}
