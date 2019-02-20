package com.guhui.common.service;



import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;

import java.util.List;


/**
 * Created by guhui ^-^ on 2018/12/20.
 */
public interface IGdStoreService {

	GdStoreVO getGdStore();

	BizResult<List<GdStoreVO>> getGdStoreAll();

}
