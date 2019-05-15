package com.guhui.admin;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * Created by guhui ^-^ on 2019/1/16.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/1/16$ 11:57$
 * @Version: 1.0
 */
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class BootAdminApp {

	public static void main(String[] args){
		SpringApplication.run(BootAdminApp.class,args);
		System.out.println("BootAdminApp服务启动完成");
	}

}
