package com.guhui.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project boyguhui-parent
 * @Description: 测试网关转发
 * @Author: guhui
 * @Date: 2019/12/26
 */
@RestController
public class GatewayController {

	@GetMapping("/routesOne")
	public void routesOne(){
		System.out.println("routesOne");
	}

	@GetMapping("/defaultFallback")
	public String defaultFallback(){
		return "defaultFallback";
	}

}
