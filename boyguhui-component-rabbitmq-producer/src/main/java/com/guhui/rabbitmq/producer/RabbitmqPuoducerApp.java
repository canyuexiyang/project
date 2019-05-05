package com.guhui.rabbitmq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: mq启动类
 * @Author: guhui
 * @CreateDate: 2019/4/29$ 17:20$
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.guhui.rabbitmq")
public class RabbitmqPuoducerApp {

//	public static void main(String[] args){
//		SpringApplication.run(RabbitmqPuoducerApp.class,args);
//		System.out.println("rabbitmq-puoducer服务启动成功");
//	}

	public static void main(String[] args){
		SpringApplication.run(RabbitmqPuoducerApp.class,args);
		System.out.println("rabbitmq-puoducer服务启动成功");
	}

}
