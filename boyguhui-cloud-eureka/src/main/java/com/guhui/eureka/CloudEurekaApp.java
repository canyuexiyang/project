package com.guhui.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by guhui ^-^ on 2018/12/7.
 */
@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaApp {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaApp.class, args);
		System.out.println("CloudEurekaApp注册中心启动成功！");
	}

}