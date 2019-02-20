package com.guhui.order.controller;


import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;
import com.guhui.order.service.IGdStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * Created by guhui ^-^ on 2019/1/15.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/1/15$ 14:21$
 * @Version: 1.0
 */
@RestController
@RequestMapping("/order")
public class GdStoreController {

	private static final Logger log = LoggerFactory.getLogger(GdStoreController.class);

	@Autowired
	private IGdStoreService gdStoreService;

	@PostMapping("/getGdStoreById")
	public BizResult<GdStoreVO> getGdStoreById(@RequestBody GdStoreVO gdStoreVO) throws InterruptedException {
		return this.gdStoreService.getGdStoreById(gdStoreVO);
	}


}
