package com.guhui.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by guhui ^-^ on 2018/12/7.
 */
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.guhui.common")
@ImportResource("classpath:spring/spring-context.xml")
public class CommonServiceApp {

    public static void main(String[] args){
        SpringApplication.run(CommonServiceApp.class,args);
        System.out.println("boyguhui-common-service服务启动成功");
    }

}
