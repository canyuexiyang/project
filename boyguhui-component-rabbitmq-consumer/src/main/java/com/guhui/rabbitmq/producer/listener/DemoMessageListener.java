package com.guhui.rabbitmq.producer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息消费者
 * @Author: guhui
 * @CreateDate: 2019/4/29$ 18:43$
 */
@Component
public class DemoMessageListener {

	@RabbitListener(queues = "My-One-Queue")
	public void oneConsumer(String message){
		System.out.println("我是My-One-Queue队列-message:"+message);
	}

	@RabbitListener(queues = "My-Two-Queue")
	public void twoConsumer(String message){
		System.out.println("我是My-Two-Queue队列-message:"+message);
	}

	@RabbitListener(queues = "My-Three-Queue")
	public void threeConsumer(String message){
		System.out.println("我是My-Three-Queue队列-message:"+message);
	}

}
