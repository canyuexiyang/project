package com.guhui.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/5/5$ 16:18$
 */
@Configuration
public class DirectExchangeAndBindingConfiguration {

	@Bean(name = "myDirectExchange")
	DirectExchange getDirectExchange(){
		return new DirectExchange("My-Direct-Exchange");
	}

	@Bean
	Binding bindingQueueOneToDirectExchange(
			@Qualifier("myOneQueue") Queue myOneQueue,
			@Qualifier("myDirectExchange") DirectExchange myDirectExchange){
		return BindingBuilder.bind(myOneQueue).to(myDirectExchange).with("boyguhui");
	}

}
