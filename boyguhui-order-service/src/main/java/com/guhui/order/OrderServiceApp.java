package com.guhui.order;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by guhui ^-^ on 2019/1/15.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/1/15$ 14:25$
 * @Version: 1.0
 */
@Configuration
@EnableApolloConfig
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.guhui.order")
@ImportResource("classpath:spring/spring-context.xml")
public class OrderServiceApp {

	public static void main(String[] args){
		SpringApplication.run(OrderServiceApp.class,args);
		System.out.println("boyguhui-order-service服务启动成功");
	}

}
