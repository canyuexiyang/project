package com.guhui.idempotence.service;

import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;

/**
 * Created by guhui ^-^ on 2019/8/15.
 *
 * @Description: 新增GdStore记录
 */
public interface GdStoreService {

	BizResult<Long> inster(GdStoreVO vo);

}
