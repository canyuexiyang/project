package com.guhui.consul.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/5/15$ 15:08$
 */
@RestController
public class ConsumerController {

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Resource
	private RestTemplate restTemplate;

	@GetMapping("/test")
	public String test(){
		System.out.println("boyguhui-consul-consumer");
		return "hello consul consumer";
	}

	@GetMapping("/call")
	public String call(){
		ServiceInstance serviceInstance = loadBalancer.choose("boyguhui-consul-producer");
		System.out.println("服务器地址："+serviceInstance.getUri());
		System.out.println("服务器名称："+serviceInstance.getServiceId());
		String response = restTemplate.getForObject(serviceInstance.getUri()+"/test",String.class);
		System.out.println(response);
		return response;
	}
}
