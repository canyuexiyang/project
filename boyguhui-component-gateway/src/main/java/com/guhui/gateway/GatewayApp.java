package com.guhui.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * @Project boyguhui-parent
 * @Description: 网关服务启动类
 * @Author: guhui
 * @Date: 2019/12/26
 */
@SpringBootApplication
@EnableCircuitBreaker
public class GatewayApp {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApp.class,args);
		System.out.println("boyguhui-component-gateway,网关服务启动成功");
	}

}
