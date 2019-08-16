package com.guhui.idempotence.service.impl;

import com.guhui.idempotence.service.GdStoreService;
import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.feign.GdStoreFeignClient;
import com.guhui.order.api.vo.GdStoreVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Project boyguhui-parent
 * @Description: 新增GdStore记录实现类
 * @Author: guhui
 * @Date: 2019/8/15
 */
@Service
public class GdStoreServiceImpl implements GdStoreService{

	@Resource
	private GdStoreFeignClient gdStoreFeignClient;

	@Override
	public BizResult<Long> inster(GdStoreVO vo) {
		return this.gdStoreFeignClient.inster(vo);
	}
}
