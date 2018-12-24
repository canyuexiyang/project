package com.guhui.common.controller;

import com.guhui.common.entity.BizResult;
import com.guhui.common.service.IGdStoreService;
import com.guhui.common.vo.GdStoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by guhui ^-^ on 2018/12/20.
 */
@RestController
@RequestMapping("/gdStore")
public class GdStoreController {

	@Autowired
	private IGdStoreService gdStoreService;

	@GetMapping("/getGdStoreAll")
	public BizResult<List<GdStoreVO>> getGdStoreAll(){
		return this.gdStoreService.getGdStoreAll();
	}

}
