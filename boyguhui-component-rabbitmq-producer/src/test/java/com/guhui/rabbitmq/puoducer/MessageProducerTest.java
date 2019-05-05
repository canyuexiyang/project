package com.guhui.rabbitmq.puoducer;

import com.guhui.rabbitmq.producer.RabbitmqPuoducerApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * @Description: 消息生产测试类
 * @Author: guhui
 * @CreateDate: 2019/4/29$ 18:49$
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqPuoducerApp.class)
public class MessageProducerTest {

	/** 装配AMQP模版 */
	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * fanout路由策略（即:广播模式）测试
	 */
	@Test
	public void fanoutExchangeTest(){
		System.out.println("--------------");
		amqpTemplate.convertAndSend("My-Fanout-Exchange","","生产者第一条消息");
	}

}
