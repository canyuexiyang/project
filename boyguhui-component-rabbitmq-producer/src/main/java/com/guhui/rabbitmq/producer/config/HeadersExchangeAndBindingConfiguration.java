package com.guhui.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/5/6$ 17:09$
 */
@Configuration
public class HeadersExchangeAndBindingConfiguration {

	@Bean(name = "myHeadersExchange")
	HeadersExchange getHeadersExchange(){
		return new HeadersExchange("My-Headers-Exchange");
	}

	@Bean
	Binding bindingQueueOneToHeadersAllExchange(@Qualifier("myOneQueue")Queue myOneQueue,@Qualifier("myHeadersExchange") HeadersExchange myHeadersExchange) {
		Map<String, Object> header = new HashMap<>();
		header.put("name", "guhui");
		header.put("age", "18");
		return BindingBuilder.bind(myOneQueue).to(myHeadersExchange).whereAll(header).match();
	}

	@Bean
	Binding bindingQueueTwoToHeadersAllExchange(@Qualifier("myTwoQueue")Queue myTwoQueue,@Qualifier("myHeadersExchange") HeadersExchange myHeadersExchange) {
		Map<String, Object> header = new HashMap<>();
		header.put("name", "guhui");
		header.put("age", "18");
		return BindingBuilder.bind(myTwoQueue).to(myHeadersExchange).whereAny(header).match();
	}

}
