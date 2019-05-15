package com.guhui.consul.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: consul 生产者
 * @Author: guhui
 * @CreateDate: 2019/5/15$ 14:53$
 */
@Configuration
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulConsumerApp {

	public static void main(String[] args){
		SpringApplication.run(ConsulConsumerApp.class,args);
		System.out.println("ConsulConsumer服务启动成功");
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
