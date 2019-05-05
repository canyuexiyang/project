package com.guhui.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 创建fanout路由策略机
 * @Author: guhui
 * @CreateDate: 2019/4/29$ 17:56$
 */
@Configuration
public class FanoutExchangeAndBindingConfiguration {

	/**
	 * 注入Fanout路由策略的Exchange交换机实例
	 * @return
	 */
	@Bean(name = "myFanoutExchange")
	FanoutExchange getFanoutExchange(){
		return new FanoutExchange("My-Fanout-Exchange");
	}

	/**
	 * 将myOneQueue对应的队列,绑定到myFanoutExchange对应的交换机
	 * @param myOneQueue
	 * @param myFanoutExchange
	 * @return
	 */
	@Bean
	Binding bindingQueueOneToFanoutExchange(
			@Qualifier("myOneQueue") Queue myOneQueue,
			@Qualifier("myFanoutExchange") FanoutExchange myFanoutExchange){
		return BindingBuilder.bind(myOneQueue).to(myFanoutExchange);
	}

	/**
	 * 将myTwoQueue对应的队列,绑定到myFanoutExchange对应的交换机
	 * @param myTwoQueue
	 * @param myFanoutExchange
	 * @return
	 */
	@Bean
	Binding bindingQueueTwoToFanoutExchange(
			@Qualifier("myTwoQueue") Queue myTwoQueue,
			@Qualifier("myFanoutExchange") FanoutExchange myFanoutExchange){
		return BindingBuilder.bind(myTwoQueue).to(myFanoutExchange);
	}

	/**
	 * 将myThreeQueue对应的队列,绑定到myFanoutExchange对应的交换机
	 * @param myThreeQueue
	 * @param myFanoutExchange
	 * @return
	 */
	@Bean
	Binding bindingQueueThreeToFanoutExchange(
			@Qualifier("myThreeQueue") Queue myThreeQueue,
			@Qualifier("myFanoutExchange") FanoutExchange myFanoutExchange){
		return BindingBuilder.bind(myThreeQueue).to(myFanoutExchange);
	}

}
