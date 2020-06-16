package com.guhui.feign.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * @Project boyguhui-cloud-feign-consumer
 * @Description: java类作用描述
 * @Author: guhui
 * @Date: 2019/8/8$
 */
@Configuration
@EnableHystrix
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.guhui")
@ComponentScan(basePackages = "com.guhui.feign.consumer")
public class FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class,args);
		System.out.println("boyguhui-cloud-feign-consumer服务启动成功");
	}

}
