package com.guhui.demo.admin.service;

import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;

/**
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/8/7$ 14:29$
 */
public interface TestApi {

	BizResult<GdStoreVO> findId(GdStoreVO gdStoreVO);

}
