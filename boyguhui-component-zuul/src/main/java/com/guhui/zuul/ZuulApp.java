package com.guhui.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/4/10$ 15:32$
 * @EnableZuulProxy 注解说明：@EnableZuulProxy简单理解为@EnableZuulServer的增强版，
 * 当Zuul与Eureka、Ribbon等组件配合使用时，我们使用@EnableZuulProxy。
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableEurekaClient
public class ZuulApp {

	public static void main(String[] args){
		SpringApplication.run(ZuulApp.class,args);
		System.out.println("zuul网关服务启动成功");
	}

}
