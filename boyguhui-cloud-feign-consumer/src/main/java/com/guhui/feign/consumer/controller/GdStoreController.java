package com.guhui.feign.consumer.controller;

import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.feign.GdStoreFeignClient;
import com.guhui.order.api.vo.GdStoreVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guhui ^-^ on 2018/12/20.
 */
@RestController
@RequestMapping("/test")
public class GdStoreController {

	private static final Logger log = LoggerFactory.getLogger(GdStoreController.class);

	@Resource
	private GdStoreFeignClient gdStoreFeignClient;


	@PostMapping("/getGdStoreById")
	public BizResult<GdStoreVO> getGdStoreById(@RequestBody GdStoreVO gdStoreVO){
		return this.gdStoreFeignClient.getGdStoreById(gdStoreVO);
	}


}
