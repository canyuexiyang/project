package com.guhui.es.bboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EsBbossApp {

	public static void main(String[] args){
		SpringApplication.run(EsBbossApp.class,args);
		System.out.println("Es-Bboss服务启动成功");
	}

}
