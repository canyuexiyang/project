package com.guhui.idempotence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Project boyguhui-parent
 * @Description: 幂等性项目启动入口
 * @Author: guhui
 * @Date: 2019/8/15
 */
@Configuration
@EnableFeignClients(basePackages = "com.guhui")
@ComponentScan(basePackages = "com.guhui.idempotence")
@EnableEurekaClient
@SpringBootApplication
public class EmpotenceApp {

	public static void main(String[] args) {
		SpringApplication.run(EmpotenceApp.class,args);
		System.out.println("EmpotenceApp服务启动成功买");
	}

}
