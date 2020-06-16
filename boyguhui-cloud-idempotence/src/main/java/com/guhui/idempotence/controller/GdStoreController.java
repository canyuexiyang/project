package com.guhui.idempotence.controller;

import com.guhui.idempotence.annotation.IdempotentToken;
import com.guhui.idempotence.service.GdStoreService;
import com.guhui.idempotence.util.MapUtil;
import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project boyguhui-parent
 * @Description: 新增数据库记录
 * @Author: guhui
 * @Date: 2019/8/15
 */
@RestController
public class GdStoreController {

	@Autowired
	private GdStoreService gdStoreService;

	/**
	 * 新增记录
	 * @param gdStoreVO
	 * @return 返回新增记录主键
	 */
	@IdempotentToken(value = true)
	@PostMapping("inster")
	public BizResult<Long> inster(@RequestBody GdStoreVO gdStoreVO){
		return this.gdStoreService.inster(gdStoreVO);
	}

	/**
	 * 查询map数量大小
	 * @return
	 */
	@GetMapping("getMapSize")
	public BizResult<Integer> getMapSize(){
		Integer size = MapUtil.getMap().size();
		return BizResult.success(size);
	}

}
