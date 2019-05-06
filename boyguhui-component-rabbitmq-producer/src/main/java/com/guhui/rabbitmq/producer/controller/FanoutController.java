package com.guhui.rabbitmq.producer.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
		return "success";
	}

	@GetMapping("/directTest")
	public String directExchangeTest(){
		System.out.println("--------------");
		amqpTemplate.convertAndSend("My-Direct-Exchange","boyguhui","生产者第二条消息:nishizhuma");
		return "success";
	}

	@GetMapping("/topicTest")
	public String topicExchangeTest(){
		System.out.println("--------------");
		amqpTemplate.convertAndSend("My-Topic-Exchange","boyguhuiKey.guhui","生产者Topic[boyguhuiKey]类型消息:nishizhuma");
		amqpTemplate.convertAndSend("My-Topic-Exchange","guhui.topic","生产者Topic[topic]类型消息:nicaishizhu");
		amqpTemplate.convertAndSend("My-Topic-Exchange","guhui","生产者Topic[#]类型消息:开黑不,我辅助贼六");
		return "success";
	}

	@GetMapping("/headerTest")
	public String headerExchangeTest(){
		System.out.println("--------------");
		Map<String, Object> header = new HashMap<>();
		header.put("name", "guhui");
		header.put("age", "18");
		amqpTemplate.convertAndSend("My-Headers-Exchange","","【头交换机】全部匹配", (MessagePostProcessor) header);

		Map<String, Object> headerAny = new HashMap<>();
		headerAny.put("name", "pyf");
		headerAny.put("age", "18");
		amqpTemplate.convertAndSend("My-Headers-Exchange","","【头交换机】匹配其中一个",(MessagePostProcessor) headerAny);
		return "success";
	}
}
