package com.guhui.demo.admin.service.imp;

import com.guhui.demo.admin.service.TestApi;
import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.feign.GdStoreFeignClient;
import com.guhui.order.api.vo.GdStoreVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/8/7$ 14:30$
 */
@Service
public class TestApiImpl implements TestApi {

	@Resource
	private GdStoreFeignClient gdStoreFeignClient;

	@Cacheable(cacheNames = "users",key = "#gdStoreVO.gdId")
	@Override
	public BizResult<GdStoreVO> findId(GdStoreVO gdStoreVO) {
		System.out.println(" --- 查询数据库----");
		return gdStoreFeignClient.getGdStoreById(gdStoreVO);
	}
}
