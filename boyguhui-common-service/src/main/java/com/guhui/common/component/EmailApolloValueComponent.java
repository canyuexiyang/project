package com.guhui.common.component;


import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;


@Configuration
public class EmailApolloValueComponent implements BeanPostProcessor {

	@Autowired
	private Environment environment;

	@PostConstruct
	public void test(){


	}
}
