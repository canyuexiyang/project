package com.guhui.order.service;



import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;

import java.util.List;

/**
 * Created by guhui ^-^ on 2018/12/20.
 */
public interface IGdStoreService {

	GdStoreVO getGdStore();

	BizResult<List<GdStoreVO>> getGdStoreAll();

	BizResult<GdStoreVO> getGdStoreById(GdStoreVO gdStoreVO);

	BizResult<GdStoreVO> findId(Integer gdId);

	BizResult<GdStoreVO> getGdStoreByRemark(GdStoreVO gdStoreVO);

	BizResult<GdStoreVO> updateById(GdStoreVO gdStoreVO);

	BizResult deleteById(GdStoreVO gdStoreVO);

	BizResult<Long> inster(GdStoreVO vo);
}
