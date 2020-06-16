package com.guhui.idempotence.interceptor;

import com.guhui.idempotence.annotation.IdempotentToken;
import com.guhui.idempotence.exception.DefaultException;
import com.guhui.idempotence.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Project boyguhui-parent
 * @Description: Controller接口幂等性拦截器
 * @Author: guhui
 * @Date: 2019/8/15
 */
public class IdEmpotentTokenInterceptor implements HandlerInterceptor {

	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// 返回false 退回请求，返回true 通过，放行
		if (!(handler instanceof HandlerMethod)){
			throw new DefaultException("ERROR-inster");
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		//通过反射拿到方法和方法上的注解
		Method method = handlerMethod.getMethod();
		IdempotentToken token = method.getAnnotation(IdempotentToken.class);
		if (null != token){
			if (token.value()){
				//验证Token bool=false代表验证失败，true代表验证成功
				boolean bool = this.tokenService.checkToken(request);
				if (!bool){
					throw new DefaultException("ERROR-inster");
				}
			}
		}
		return true;
	}
}
