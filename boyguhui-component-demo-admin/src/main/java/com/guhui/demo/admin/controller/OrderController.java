package com.guhui.demo.admin.controller;

import com.guhui.demo.admin.service.TestApi;
import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.feign.GdStoreFeignClient;
import com.guhui.order.api.vo.GdStoreVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by guhui ^-^ on 2019/2/18.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/2/18$ 11:48$
 * @Version: 1.0
 */
@RestController
public class OrderController {

	@Value("${server.port}")
	private String port;

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private GdStoreFeignClient gdStoreFeignClient;

	@Autowired
	private TestApi testApi;

	@GetMapping("/getGdStore")
	public BizResult<GdStoreVO> getGdStore(){
		return this.gdStoreFeignClient.getGdStoreById(new GdStoreVO(1));
	}

	@GetMapping("/testOne")
	public BizResult<String> getZuulTestOne(@RequestParam("sleep") int sleep){
		try {
			Thread.sleep(sleep*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean isInfo = logger.isInfoEnabled();
		boolean isDebug = logger.isDebugEnabled();
		boolean isWarn = logger.isWarnEnabled();
		boolean isError = logger.isErrorEnabled();
		logger.info("testOne -- 【{}】",port);
		return BizResult.error("success");
	}

	@GetMapping("/foo")
	public String foo(String foo) {
		return port+ " hello "+foo+"!";
	}

	@GetMapping("/testSleep")
	public BizResult testSleep(@RequestParam("sleep") int sleep){
		logger.info("sleep..........start");
		try{
			Thread.sleep(sleep*1000);
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		logger.info("sleep..........end");
		return BizResult.success();
	}

	@PostMapping("/testTwo")
	public BizResult<GdStoreVO> test(@RequestBody GdStoreVO gdStoreVO){
		return this.testApi.findId(gdStoreVO);
	}

}
