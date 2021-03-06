package com.guhui.idempotence;


import com.guhui.idempotence.interceptor.IdEmpotentTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Token注解拦截器
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

	/** Token拦截器 */
	@Bean
	public IdEmpotentTokenInterceptor tokenInterceptor() {
		return new IdEmpotentTokenInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/** Token拦截器 */
		registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**");

	}

}
