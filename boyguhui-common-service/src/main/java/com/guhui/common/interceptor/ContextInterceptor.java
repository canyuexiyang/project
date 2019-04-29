package com.guhui.common.interceptor;

import com.guhui.common.util.RequestUtil;
import org.sagacity.sqltoy.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上下文拦截器
 * 
 * @author wen.zhang
 * @date 2018-07-06
 */
public class ContextInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(ContextInterceptor.class);

	/** 店铺ID */
	@Value("${shop.hongrun.shopId}")
	private String shopId;

	/** 店铺企业ID */
	@Value("${shop.hongrun.shopEntId}")
	private String shopEntId;

	/** 缓存服务远程接口 */
//	@Autowired
//	private CacheFeignClient cacheFeignClient;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 上下文信息初始化（防止脏数据）
		RequestContext.remove();

		try {
			RequestContext.setRequest(request);
			RequestContext.setResponse(response);

			/** 后期动态配置、须在TicketAuthInterceptor前做请求URL店铺验证拦截 */
			RequestContext.setShopId(shopId);
			RequestContext.setShopEntId(shopEntId);

			/** Token */
			String token = request.getHeader(RequestContext.TOKEN); // Header中获取
			if (StringUtil.isBlank(token)) {
				token = RequestUtil.getCookiesValue(request, RequestContext.TOKEN); // Cookie中获取
			}
			// token = Base64.getFromBase64(token);
			if (StringUtil.isNotBlank(token)) {
				RequestContext.setToken(token);
			}

			/** CAS认证令牌 */
			String casTicket = request.getParameter(RequestContext.CAS_TICKET);
			if (StringUtil.isNotBlank(casTicket)) {
				RequestContext.setCasTicket(casTicket);
			}

			/** 企业Id */
			String enterpriseId = request.getHeader(RequestContext.ENT_ID); // Header中获取
			if (StringUtil.isBlank(enterpriseId)) {
				enterpriseId = RequestUtil.getCookiesValue(request, RequestContext.ENT_ID); // Cookie中获取
			}
			if (StringUtil.isNotBlank(enterpriseId)) {
				RequestContext.setEnterpriseId(enterpriseId);
			}

			/** 会员信息 */
			if (StringUtil.isNotBlank(token)) {
				try {
					// 通过token从Cache中获取会员信息
//					String tokenDataJson = cacheFeignClient.get(token);
//					logger.info("TokenDataJson [{}]", tokenDataJson);

//					if (StringUtil.isNotBlank(tokenDataJson)) {
//						TokenDataTO tokenDataTO = JSON.parseObject(tokenDataJson, TokenDataTO.class);

//						if (tokenDataTO != null) {
							// 会员Id
//							if (StringUtil.isNotBlank(tokenDataTO.getMemberId())) {
//								RequestContext.setMemberId(tokenDataTO.getMemberId());
//							}
							// 微信OpenId
//							if (StringUtil.isNotBlank(tokenDataTO.getOpenId())) {
//								RequestContext.setOpenId(tokenDataTO.getOpenId());
//							}
							// 微信会话密钥
//							if (StringUtil.isNotBlank(tokenDataTO.getSessionKey())) {
//								RequestContext.setSessionKey(tokenDataTO.getSessionKey());
//							}
//						}
//					}

					// 重新设置Seeion有效期
//					cacheFeignClient.expire(token, CacheConstant.CACHE_SEESION_EXPIRE_TIMES);
				} catch (Exception e) {
					logger.error("----- 会员信息解析Exception -----", e);
					e.printStackTrace();
				}
			}

			/** 原始请求URL */
			String orgUrl = RequestUtil.buildOriginalURL(request);
			if (StringUtil.isNotBlank(orgUrl)) {
				RequestContext.setOrgUrl(orgUrl);
			}

		} catch (Exception e) {
			logger.error("----- ContextInterceptor preHandle Exception -----", e);
			e.printStackTrace();
		}

		logger.info("RequestContext.shopId：" + RequestContext.getShopId());
		logger.info("RequestContext.shopEntId：" + RequestContext.getShopEntId());
		logger.info("RequestContext.token：" + RequestContext.getToken());
		logger.info("RequestContext.casTicket：" + RequestContext.getCasTicket());
		logger.info("RequestContext.enterpriseId：" + RequestContext.getEnterpriseId());
		logger.info("RequestContext.memberId：" + RequestContext.getMemberId());
		logger.info("RequestContext.openId：" + RequestContext.getOpenId());
		logger.info("RequestContext.sessionKey：" + RequestContext.getSessionKey());
		logger.info("RequestContext.orgUrl：" + RequestContext.getOrgUrl());
		logger.info("RequestContext.ip：" + RequestUtil.getIpAddress(request));

		return true;
	}

	/**
	 * 数据销毁
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// 销毁用户上下文信息（防止脏数据）
		RequestContext.remove();
	}

}
