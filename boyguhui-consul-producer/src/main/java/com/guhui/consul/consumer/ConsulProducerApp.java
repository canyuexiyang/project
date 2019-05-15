package com.guhui.consul.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: consul 生产者
 * @Author: guhui
 * @CreateDate: 2019/5/15$ 14:53$
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulProducerApp {

	public static void main(String[] args){
		SpringApplication.run(ConsulProducerApp.class,args);
		System.out.println("ConsulProducer服务启动成功");
	}

}
