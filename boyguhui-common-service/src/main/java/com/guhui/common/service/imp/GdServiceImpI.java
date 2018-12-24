package com.guhui.common.service.imp;

import com.guhui.common.entity.BizResult;
import com.guhui.common.service.IGdStoreService;
import com.guhui.common.vo.GdStoreVO;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guhui ^-^ on 2018/12/20.
 */
@Service
public class GdServiceImpI implements IGdStoreService {

	@Autowired
	private SqlToyLazyDao sqlToyLazyDao;

	@Override
	public GdStoreVO getGdStore() {
		return null;
	}

	@Override
	public BizResult<List<GdStoreVO>> getGdStoreAll() {
		try {
			List<GdStoreVO> list = this.sqlToyLazyDao.findBySql("gd_store_all",new String[]{},new String[]{},GdStoreVO.class);
			return BizResult.success(list);
		}catch (Exception e){
			return BizResult.error();
		}
	}
}
