package com.guhui.common;


import com.guhui.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Spring配置
 * 
 * @author wen.zhang
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

	/** Token拦截器 */
	@Bean
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/** Token拦截器 */
		registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**");

	}

}
