package com.guhui.rabbitmq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: mq启动类
 * @Author: guhui
 * @CreateDate: 2019/4/29$ 17:20$
 */
@SpringBootApplication
@EnableEurekaClient
public class RabbitmqConsumerApp {

	public static void main(String[] args){
		SpringApplication.run(RabbitmqConsumerApp.class,args);
		System.out.println("rabbitmq-consumer服务启动成功");
	}

}
