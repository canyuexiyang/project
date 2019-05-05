package com.guhui.rabbitmq.producer.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description: queue队列注入
 * @Author: guhui
 * @CreateDate: 2019/4/29$ 17:42$
 */
@Configuration
public class QueueConfiguration {

	/**
	 * 注入一个队列，队列名叫My-One-Queue
	 * @return
	 */
	@Bean(name = "myOneQueue")
	public Queue getOneQueue(){
		return new Queue("My-One-Queue");
	}

	/**
	 * 注入一个队列，队列名叫My-Two-Queue
	 * @return
	 */
	@Bean(name = "myTwoQueue")
	public Queue getTwoQueue(){
		return new Queue("My-Two-Queue");
	}

	/**
	 * 注入一个队列，队列名叫My-Three-Queue
	 * @return
	 */
	@Bean(name = "myThreeQueue")
	public Queue getThreeQueue(){
		return new Queue("My-Three-Queue");
	}

}
