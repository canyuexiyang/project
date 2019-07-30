package com.guhui.order.service.imp;


import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;
import com.guhui.order.service.IGdStoreService;
import org.sagacity.sqltoy.callback.UpdateRowHandler;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.executor.QueryExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
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

	@Cacheable(cacheNames = "users",key = "#gdStoreVO.gdId")
	@Override
	public BizResult<GdStoreVO> getGdStoreById(GdStoreVO gdStoreVO) {
		try {
			gdStoreVO = (GdStoreVO) this.sqlToyLazyDao.loadBySql("getGdStoreById",new String[]{"gdId"},new Object[]{gdStoreVO.getGdId()},GdStoreVO.class);
			log.info("getGdStoreById -- key = gdId");
			return BizResult.success(gdStoreVO);
		}catch (Exception e){
			log.error("-----GdServiceImpI -> getGdStoreById 方法错误,message:{},stackTrace:{}",e.getMessage(),e.getStackTrace());
			return BizResult.error();
		}
	}

	@Override
	public BizResult<GdStoreVO> findId(Integer gdId) {
		try {
			GdStoreVO gdStoreVO = new GdStoreVO(gdId);
			gdStoreVO = (GdStoreVO) this.sqlToyLazyDao.load(gdStoreVO);
			return BizResult.success(gdStoreVO);
		}catch (Exception e){
			log.error("-----GdServiceImpI -> findId 方法错误,message:{},stackTrace:{}",e.getMessage(),e.getStackTrace());
			return BizResult.error();
		}
	}

	@Cacheable(cacheNames = "users",key = "#gdStoreVO.remark")
	@Override
	public BizResult<GdStoreVO> getGdStoreByRemark(GdStoreVO gdStoreVO) {
		try {
			gdStoreVO = (GdStoreVO) this.sqlToyLazyDao.loadBySql("getGdStoreByRemark",new String[]{"remark"},new Object[]{gdStoreVO.getRemark()},GdStoreVO.class);
			log.info("getGdStoreByRemark -- key = remark");
			return BizResult.success(gdStoreVO);
		}catch (Exception e){
			log.error("-----GdServiceImpI -> getGdStoreByRemark 方法错误,message:{},stackTrace:{}",e.getMessage(),e.getStackTrace());
			return BizResult.error();
		}
	}

	@CachePut(cacheNames = "users",key = "#a0.gdId")
	@Override
	public BizResult<GdStoreVO> updateById(GdStoreVO gdStoreVO) {
		try {
			QueryExecutor queryExecutor = new QueryExecutor("getGdStoreById",gdStoreVO);
			String remark = gdStoreVO.getRemark();
			gdStoreVO = (GdStoreVO) this.sqlToyLazyDao.updateFetch(queryExecutor, new UpdateRowHandler() {
				@Override
				public void updateRow(ResultSet resultSet, int i) throws Exception {
					resultSet.updateString("remark",remark);
				}
			}).get(0);
			log.info("updateById -- key = gdId");
			return BizResult.success(gdStoreVO);
		}catch (Exception e){
			log.error("-----GdServiceImpI -> updateById 方法错误,message:{},stackTrace:{}",e.getMessage(),e.getStackTrace());
			return BizResult.error();
		}
	}

	@CacheEvict(cacheNames = "users",key = "#a0.gdId")
	@Override
	public BizResult deleteById(GdStoreVO gdStoreVO) {
		try {
			this.sqlToyLazyDao.delete(gdStoreVO);
		}catch (Exception e){
			log.error("-----GdServiceImpI -> deleteById 方法错误,message:{},stackTrace:{}",e.getMessage(),e.getStackTrace());
			return BizResult.error();
		}
		return BizResult.success();
	}
}
