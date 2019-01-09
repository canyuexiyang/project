package com.guhui.common.component;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guhui ^-^ on 2019/1/9.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/1/9$ 15:01$
 * @Version: 1.0
 */
@Configuration
public class HystrixConfig {

	@Bean
	public HystrixMetricsStreamServlet hystrixMetricsStreamServlet(){
		return new HystrixMetricsStreamServlet();
	}

	@Bean
	public ServletRegistrationBean registration(HystrixMetricsStreamServlet servlet){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean();
		registrationBean.setServlet(servlet);
		//是否启用该registrationBean
		registrationBean.setEnabled(true);
		registrationBean.addUrlMappings("/hystrix.stream");
		return registrationBean;
	}

}
