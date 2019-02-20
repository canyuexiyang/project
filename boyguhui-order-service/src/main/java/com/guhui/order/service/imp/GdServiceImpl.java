package com.guhui.order.service.imp;


import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;
import com.guhui.order.service.IGdStoreService;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guhui ^-^ on 2018/12/20.
 * @author : guhui
 */
@Service
public class GdServiceImpl implements IGdStoreService {

	private static final Logger log = LoggerFactory.getLogger(GdServiceImpl.class);

	@Autowired
	private SqlToyLazyDao sqlToyLazyDao;

	@Override
	public GdStoreVO getGdStore() {
		return null;
	}

	@Override
	public BizResult<List<GdStoreVO>> getGdStoreAll() {
		try {
			log.info("------查询所有的GdServiceImpi----");
			List<GdStoreVO> list = this.sqlToyLazyDao.findBySql("gd_store_all",new String[]{},new String[]{},GdStoreVO.class);
			return BizResult.success(list);
		}catch (Exception e){
			log.error("-----GdServiceImpI -> getGdStoreAll 方法错误");
			log.error(e.getMessage(),e.getLocalizedMessage(),e.getCause(),e.getStackTrace());
			return BizResult.error();
		}
	}

	@Override
	public BizResult<GdStoreVO> getGdStoreById(GdStoreVO gdStoreVO) {
		try {
			gdStoreVO = (GdStoreVO) this.sqlToyLazyDao.loadBySql("getGdStoreById",new String[]{"gdId"},new Object[]{gdStoreVO.getGdId()},GdStoreVO.class);
			return BizResult.success(gdStoreVO);
		}catch (Exception e){
			log.error("-----GdServiceImpI -> getGdStoreById 方法错误");
			log.error(e.getMessage(),e.getStackTrace());
			return BizResult.error();
		}
	}
}
