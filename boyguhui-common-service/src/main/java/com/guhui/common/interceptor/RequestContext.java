package com.guhui.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求上下文信息
 * 
 * @author wen.zhang
 * @date 2018-07-06
 */
public class RequestContext {

	/** 【Cookie】Token */
	public static final String TOKEN = "token";
	/** 【Cookie】EntId */
	public static final String ENT_ID = "entid";
	/** 【Request】CasTicket */
	public static final String CAS_TICKET = "ticket";

	/** 【Context】Request信息 */
	private static final ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	/** 【Context】Response信息 */
	private static final ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();

	/** 【Shop】店铺ID */
	private static final ThreadLocal<String> shopId = new ThreadLocal<String>();
	/** 【Shop】店铺企业ID */
	private static final ThreadLocal<String> shopEntId = new ThreadLocal<String>();

	/** 【User】Token */
	private static final ThreadLocal<String> token = new ThreadLocal<String>();
	/** 【User】CAS认证令牌 */
	private static final ThreadLocal<String> casTicket = new ThreadLocal<String>();
	/** 【User】企业Id */
	private static final ThreadLocal<String> enterpriseId = new ThreadLocal<String>();
	/** 【User】用户Id */
	private static final ThreadLocal<String> memberId = new ThreadLocal<String>();
	/** 【User】微信OpenId */
	private static final ThreadLocal<String> openId = new ThreadLocal<String>();
	/** 【User】微信会话密钥 */
	private static final ThreadLocal<String> sessionKey = new ThreadLocal<String>();
	/** 【User】原始请求URL */
	private static final ThreadLocal<String> orgUrl = new ThreadLocal<String>();

	private RequestContext() {
	}

	public static HttpServletRequest getRequest() {
		return RequestContext.request.get();
	}

	protected static void setRequest(HttpServletRequest request) {
		RequestContext.request.set(request);
	}

	public static HttpServletResponse getResponse() {
		return RequestContext.response.get();
	}

	protected static void setResponse(HttpServletResponse response) {
		RequestContext.response.set(response);
	}

	public static String getShopId() {
		return RequestContext.shopId.get();
	}

	protected static void setShopId(String shopId) {
		RequestContext.shopId.set(shopId);
	}

	public static String getShopEntId() {
		return RequestContext.shopEntId.get();
	}

	protected static void setShopEntId(String shopEntId) {
		RequestContext.shopEntId.set(shopEntId);
	}

	public static String getToken() {
		return RequestContext.token.get();
	}

	protected static void setToken(String token) {
		RequestContext.token.set(token);
	}

	public static String getCasTicket() {
		return RequestContext.casTicket.get();
	}

	protected static void setCasTicket(String casTicket) {
		RequestContext.casTicket.set(casTicket);
	}

	public static String getEnterpriseId() {
		return RequestContext.enterpriseId.get();
	}

	protected static void setEnterpriseId(String enterpriseId) {
		RequestContext.enterpriseId.set(enterpriseId);
	}

	public static String getMemberId() {
		return RequestContext.memberId.get();
	}

	protected static void setMemberId(String memberId) {
		RequestContext.memberId.set(memberId);
	}

	public static String getOpenId() {
		return RequestContext.openId.get();
	}

	protected static void setOpenId(String openId) {
		RequestContext.openId.set(openId);
	}

	public static String getSessionKey() {
		return RequestContext.sessionKey.get();
	}

	protected static void setSessionKey(String sessionKey) {
		RequestContext.sessionKey.set(sessionKey);
	}

	public static String getOrgUrl() {
		return RequestContext.orgUrl.get();
	}

	protected static void setOrgUrl(String orgUrl) {
		RequestContext.orgUrl.set(orgUrl);
	}

	protected static void remove() {
		RequestContext.request.remove();
		RequestContext.response.remove();
		RequestContext.shopId.remove();
		RequestContext.shopEntId.remove();
		RequestContext.token.remove();
		RequestContext.casTicket.remove();
		RequestContext.enterpriseId.remove();
		RequestContext.memberId.remove();
		RequestContext.openId.remove();
		RequestContext.sessionKey.remove();
		RequestContext.orgUrl.remove();
	}

	public static void logout() {
		RequestContext.shopId.remove();
		RequestContext.shopEntId.remove();
		RequestContext.token.remove();
		RequestContext.casTicket.remove();
		RequestContext.enterpriseId.remove();
		RequestContext.memberId.remove();
		RequestContext.openId.remove();
		RequestContext.sessionKey.remove();
	}

}
