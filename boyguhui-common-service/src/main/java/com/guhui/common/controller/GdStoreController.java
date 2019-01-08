package com.guhui.common.controller;

import com.guhui.common.CommonServiceApp;
import com.guhui.common.entity.BizResult;
import com.guhui.common.service.IGdStoreService;
import com.guhui.common.service.imp.GdServiceImpI;
import com.guhui.common.vo.GdStoreVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.List;

/**
 * Created by guhui ^-^ on 2018/12/20.
 */
@RestController
@RequestMapping("/gdStore")
public class GdStoreController {

	private static final Logger log = LoggerFactory.getLogger(GdStoreController.class);

	@Autowired
	private IGdStoreService gdStoreService;

	@Value("${encrypt.test.sign}")
	private String sign;

	@GetMapping("/getGdStoreAll")
	public BizResult<List<GdStoreVO>> getGdStoreAll(){
		return this.gdStoreService.getGdStoreAll();
	}

	@GetMapping("/emailInfo")
	public BizResult<String> emailInfo(){
		log.info("---info---");
		try {
			String name = CommonServiceApp.getGuhuiNameValue();
			log.info(sign);
			log.info(name);
			return BizResult.success(name);
		}catch (Exception e){
			log.error("---error---");
			return BizResult.error();
		}
	}

	@GetMapping("/emailError")
	public BizResult<Boolean> emailError(){
		log.info("---info---");
		try {
			log.warn("---warn---");
			int a = 1 / 0;
		}catch (Exception e){
			log.warn("---warn---");
			log.error("Exception",e);
		}
		return BizResult.success(Boolean.TRUE);
	}

}
