package com.guhui.feign.consumer.component;

import com.guhui.order.api.feign.GdStoreFeignFallBack;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Project boyguhui-parent
 * @Description: Feign配置类
 * @Author: guhui
 * @Date: 2019/8/8
 */
//@Configuration
//public class FooConfiguration {
//	@Bean
//	@Scope("prototype")
//	public Feign.Builder feignBuilder() {
//		return Feign.builder();
//	}
//
//	@Bean
//	public GdStoreFeignFallBack fb(){
//		return new GdStoreFeignFallBack();
//	}
//
//}
