package com.guhui.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/5/6$ 16:43$
 */
@Configuration
public class TopicExchangeAndBindingConfiguration {

	@Bean(name = "myTopicExchange")
	TopicExchange getTopicExchange(){
		return new TopicExchange("My-Topic-Exchange");
	}

	@Bean
	Binding bindingQueueOneToTopicExchange(
			@Qualifier("myOneQueue")Queue myOneQueue,
			@Qualifier("myTopicExchange") TopicExchange myTopicExchange){
		return BindingBuilder.bind(myOneQueue).to(myTopicExchange).with("boyguhuiKey.#");
	}

	@Bean
	Binding bindingQueueTwoToTopicExchange(
			@Qualifier("myTwoQueue")Queue myTwoQueue,
			@Qualifier("myTopicExchange") TopicExchange myTopicExchange){
		return BindingBuilder.bind(myTwoQueue).to(myTopicExchange).with("#.topic");
	}

	@Bean
	Binding bindingQueueThreeToTopicExchange(
			@Qualifier("myThreeQueue")Queue myThreeQueue,
			@Qualifier("myTopicExchange") TopicExchange myTopicExchange){
		return BindingBuilder.bind(myThreeQueue).to(myTopicExchange).with("#");
	}

}
