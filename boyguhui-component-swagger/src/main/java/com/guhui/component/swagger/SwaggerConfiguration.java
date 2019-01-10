package com.guhui.component.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guhui ^-^ on 2019/1/10.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/1/10$ 15:49$
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class SwaggerConfiguration {

	@Value("${swagger.show}")
	private boolean swaggerShow;
	private String basePackage;
	private String creatName;
	private String serviceName;
	private String description;

	public SwaggerConfiguration() {
	}

	@Bean
	public Docket createRestApi() {


		List<Parameter> pars = new ArrayList<Parameter>();
		ParameterBuilder tokenPar = new ParameterBuilder();
		tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());
		ParameterBuilder tokenPar1 = new ParameterBuilder();
		tokenPar1.name("entid").description("entid").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

		pars.add(tokenPar1.build());

		return (new Docket(DocumentationType.SWAGGER_2))
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false)
				.enable(swaggerShow)
				.forCodeGeneration(false)
				.pathMapping("/")
				.select()
				.build()
				.apiInfo(this.apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.guhui"))
				.paths(PathSelectors.any()).build().globalOperationParameters(pars);

	}



	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(this.serviceName + " Restful APIs")
				.description("swagger接入")
				.version("1.0")
				.contact(new Contact("guhui", "guhui", "boyguhui@qq.com"))
				.build();
	}

}
