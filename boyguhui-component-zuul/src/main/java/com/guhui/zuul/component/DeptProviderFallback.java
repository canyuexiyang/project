package com.guhui.zuul.component;

import com.guhui.order.api.entity.BizResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: zuul服务降级处理
 * @Author: guhui
 * @CreateDate: 2019/4/12$ 16:41$
 */
@Component
public class DeptProviderFallback implements FallbackProvider {
	@Override
	public String getRoute() {
		//微服务配了路由的话，就用配置的名称
		//return "customers";
		//如果要为所有路由提供默认回退，可以创建FallbackProvider类型的bean并使getRoute方法返回*或null
		//return "*";
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}

			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				// 当出现服务调用错误之后返回的数据内容
				return new ByteArrayInputStream("{\"result\": \"ERROR\",\"msg\": \"Zuul-Fallback\",\"success\": false}".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				//设置响应格式为JSON
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
		};
	}
}
