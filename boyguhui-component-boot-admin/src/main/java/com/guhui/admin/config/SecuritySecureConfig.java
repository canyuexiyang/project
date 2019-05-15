package com.guhui.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/5/15$ 16:49$
 */
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

	private final String adminContextPath;

	public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
		this.adminContextPath = adminServerProperties.getContextPath();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		http.authorizeRequests()
				.antMatchers(this.adminContextPath + "/assets/**").permitAll()
				.antMatchers(this.adminContextPath + "/login").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage(this.adminContextPath + "/login")
				.successHandler(successHandler)
				.and().logout().logoutUrl(this.adminContextPath + "/logout")
				.and().httpBasic()
				.and().csrf()
				.ignoringAntMatchers(this.adminContextPath + "/instances",
						this.adminContextPath + "/actuator/**").disable();
	}

}
