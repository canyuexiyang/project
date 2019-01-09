package com.guhui.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Created by guhui ^-^ on 2019/1/9.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/1/9$ 11:38$
 * @Version: 1.0
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
@EnableEurekaClient
public class HystrixDashboardApp {

	public static void main(String[] args){
		SpringApplication.run(HystrixDashboardApp.class,args);
		System.out.println("Hystrix-Dashboard监控服务启动成功");
	}

}
