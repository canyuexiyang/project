package com.guhui.demo.admin.controller;

import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.feign.GdStoreFeignClient;
import com.guhui.order.api.vo.GdStoreVO;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Resource
	private GdStoreFeignClient gdStoreFeignClient;

	@GetMapping("/getGdStore")
	public BizResult<GdStoreVO> getGdStore(){
		return this.gdStoreFeignClient.getGdStoreById(new GdStoreVO(1));
	}

}
