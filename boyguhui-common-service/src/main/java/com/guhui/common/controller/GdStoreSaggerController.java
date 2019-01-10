package com.guhui.common.controller;

import com.guhui.common.entity.BizResult;
import com.guhui.common.service.IGdStoreService;
import com.guhui.common.vo.GdStoreVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by guhui ^-^ on 2019/1/10.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/1/10$ 16:00$
 * @Version: 1.0
 */
@Api(tags = "common-swagger-测试类")
@RestController
@RequestMapping("/swagger/common")
public class GdStoreSaggerController {

	private static final Logger log = LoggerFactory.getLogger(GdStoreController.class);

	@Autowired
	private IGdStoreService gdStoreService;

	@ApiOperation(value = "获取所有信息",notes = "查询所有")
	@GetMapping("/getGdStoreAll")
	public BizResult<List<GdStoreVO>> getGdStoreAll(){
		return this.gdStoreService.getGdStoreAll();
	}

}
