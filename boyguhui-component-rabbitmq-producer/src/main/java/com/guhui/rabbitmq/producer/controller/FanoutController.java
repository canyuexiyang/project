package com.guhui.rabbitmq.producer.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/5/5$ 11:02$
 */
@RestController
public class FanoutController {

	/** 装配AMQP模版 */
	@Autowired
	private AmqpTemplate amqpTemplate;

	@GetMapping("/fanoutTest")
	public String fanoutExchangeTest(){
		System.out.println("---------------");
		amqpTemplate.convertAndSend("My-Fanout-Exchange","","生产者第一条消息");
		System.out.println("---------------");
		return "suesscc";
	}

}
