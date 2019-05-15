package com.guhui.consul.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/5/15$ 15:08$
 */
@RestController
public class ProducerController {

	@GetMapping("/test")
	public String test(){
		System.out.println("boyguhui-consul-producer");
		return "hello consul producer";
	}
}
