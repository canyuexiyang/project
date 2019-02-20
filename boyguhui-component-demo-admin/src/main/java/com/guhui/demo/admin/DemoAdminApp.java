package com.guhui.demo.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by guhui ^-^ on 2019/2/18.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/2/18$ 11:37$
 * @Version: 1.0
 */
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.guhui.demo")
public class DemoAdminApp {

	public static void main(String[] args){
		SpringApplication.run(DemoAdminApp.class,args);
		System.out.println("DemoAdminApp服务启动成功");
	}



}
