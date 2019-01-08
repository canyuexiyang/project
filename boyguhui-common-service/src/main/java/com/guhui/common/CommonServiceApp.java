package com.guhui.common;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by guhui ^-^ on 2018/12/7.
 */
@Configuration
@EnableApolloConfig
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.guhui.common")
@ImportResource("classpath:spring/spring-context.xml")
public class CommonServiceApp {

    public static void main(String[] args){
        setProperties();
        SpringApplication.run(CommonServiceApp.class,args);
        System.out.println("boyguhui-common-service服务启动成功");
    }

    /**
     * 获取最新的apollo配置
     *  key             guhui.name  根据key获取值
     *  defaultValue    xxx         默认value
     * @return
     */
    public static String getGuhuiNameValue(){
        Config config = ConfigService.getAppConfig();
        String guhuiNameValue = config.getProperty("guhui.name","guhui-boy");
        return guhuiNameValue;
    }

    public static void setProperties(){
        Config config = ConfigService.getAppConfig();
        String value = config.getProperty("logback.env", "dev");

        String logname = config.getProperty("spring.application.name","boyguhui-common-service");
        String smtpHost = config.getProperty("email.smtpHost","smtp.163.com");
        String smtpPort = config.getProperty("email.smtpPort","25");
        String to = config.getProperty("email.to","leiyang_hui@163.com");
        String from = config.getProperty("email.from","leiyang_hui@163.com");
        String username = config.getProperty("email.username","leiyang_hui@163.com");
        String password = config.getProperty("email.password","guhui");

        System.out.println(value);
        System.out.println(logname+"-"+smtpHost+"-"+smtpPort+"-"+to+"-"+from+"-"+username+"-"+password);

        System.setProperty("spring.profiles.active", value);
        System.setProperty("spring.application.name", logname);
        System.setProperty("email.smtpHost", smtpHost);
        System.setProperty("email.smtpPort", smtpPort);
        System.setProperty("email.to", to);
        System.setProperty("email.from", from);
        System.setProperty("email.username", username);
        System.setProperty("email.password", password);
    }

}
