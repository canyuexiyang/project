package com.guhui.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guhui.common.annotation.Token;
import com.guhui.order.api.entity.Result;
import org.sagacity.sqltoy.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * WechatToken拦截器（微信专用）
 * 
 * @author wen.zhang
 * @date 2018-09-28
 */
public class WxTokenInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(WxTokenInterceptor.class);

	/** 用户企业权限服务 */
//	@Autowired
//	private UserEntAuthFeignClient userEntAuthFeignClient;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				Method method = handlerMethod.getMethod();
				Token tokenAnt = method.getAnnotation(Token.class);

				logger.info("Controller Interceptor Method：" + method.getName());
				logger.info("Controller Token Annotation：" + tokenAnt);

				// 对Controller@Token注解的方法做拦截
				if (tokenAnt != null) {
					/** 检查会员是否登录 */
					if (tokenAnt.isToken()) {
						if (!this._checkMemberId(request, response)) {
							logger.error("########## 用户未登录 ##########");

							response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401
							this._responseWriter(response, Result.error("未登录"));
							return false;
						}
					}

					/** 检查该会员是否属于该企业 */
					String enterpriseId = RequestContext.getEnterpriseId();
					RequestContext.setEnterpriseId(null);
					if (StringUtil.isNotBlank(enterpriseId) && StringUtil.isNotBlank(RequestContext.getMemberId())) {
						// BizResult<EnterpriseVO> enterpriseBizResult = enterpriseFeignClient.queryEnterpriseByEntIdAndMemberId(entId, memberId);
//						BizResult<EnterpriseMemberTO> enterpriseBizResult = this.userEntAuthFeignClient.loadAuthByCache(enterpriseId,
//								RequestContext.getMemberId());

//						if (enterpriseBizResult == null || !enterpriseBizResult.isSuccess() || !enterpriseBizResult.hasData()) {
//							logger.error("会员与企业不匹配，被拒绝访问");
//
//							response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 企业未授权【403】
//							this._responseWriter(response, Result.error("会员与企业不匹配"));
//							return false;
//						}

						logger.info("认证通过的enterpriseId：" + enterpriseId);
						RequestContext.setEnterpriseId(enterpriseId);
					}

					/** 检查企业 */
					if (tokenAnt.isEntToken()) {
						// 会员是否登录
						if (!this._checkMemberId(request, response)) {
							logger.error("########## 用户未登录 ##########");

							response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401
							this._responseWriter(response, Result.error("未登录"));
							return false;
						}

						// 企业检查
						if (StringUtil.isBlank(RequestContext.getEnterpriseId())) {
							logger.error("企业信息为空");

							response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 企业未授权【403】
							this._responseWriter(response, Result.error("未绑定企业"));
							return false;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("----- TokenInterceptor Exception -----", e);
			return false;
		}

		logger.info("----- TokenInterceptor Pass！-----");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		/** 删除Token */
		if (StringUtil.isBlank(RequestContext.getMemberId())) {
			RequestContext.setToken(null);
		}
	}

	/**
	 * 会员是否登录检查
	 */
	private boolean _checkMemberId(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
		if (StringUtil.isBlank(RequestContext.getMemberId())) {
			return false;
		}
		return true;
	}

	/**
	 * 数据写入Response
	 */
	private void _responseWriter(HttpServletResponse response, Object obj) throws UnsupportedEncodingException, IOException {
		if (obj != null) {
			PrintWriter printWriter = null;
			try {
				ObjectMapper mapper = new ObjectMapper();

				response.setContentType("application/json;charset=UTF-8");
				printWriter = response.getWriter();
				printWriter.write(mapper.writeValueAsString(obj));
				printWriter.flush();
			} catch (IOException e) {
				logger.error("response write error", e);
			} finally {
				if (printWriter != null) {
					printWriter.close();
				}
			}
		}
	}

}
