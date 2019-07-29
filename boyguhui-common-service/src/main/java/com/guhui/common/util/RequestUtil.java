package com.guhui.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class RequestUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

	public static Map<String, Object> getReqest(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"");
		// html标签过滤器
		Object from = map.get("from");
		boolean isIos = false;
		if (from != null && "ios".equals(from.toString())) {
			isIos = true;
		}
		//
		Set keySet = map.keySet();
		for (Object aKeySet : keySet) {
			String keyValue = (String) aKeySet;
			if (map.get(keyValue) instanceof String[]) {
				String[] values = (String[]) map.get(keyValue);
				for (int i = 0; i < values.length; i++) {
					String value = null;
					if (isIos) {
						value = new String(values[i].getBytes("iso-8859-1"),
								"utf-8");
					} else {
						value = values[i];
					}
					if (!"urlStr".equals(keyValue)) {
						values[i] = filter(value);
					}
				}
				map.put(keyValue, values);
			} else {
				String value = (String) map.get(keyValue);
				if (isIos) {
					value = new String(map.get(keyValue).toString()
							.getBytes("iso-8859-1"), "utf-8");
				} else {
					value = (String) map.get(keyValue);
				}
				if (!"urlStr".equals(keyValue)) {
					value = filter(value);
				}
				map.put(keyValue, value);
			}

		}

		// 判断page
		Integer pageNo = null;
		if (null != map.get("page")) {
			try {
				pageNo = Integer.parseInt(map.get("page").toString());
			} catch (Exception e) {
				throw new Exception("page转换整数时，出现异常，请确保page类型为整形!");
			}
		}
		if (null != pageNo && pageNo <= 0) {
			throw new Exception("page必须是大于0的整数！");
		}
		// 判断rows
		Integer pagezize = null;
		if (null != map.get("rows")) {
			try {
				pagezize = Integer.parseInt(map.get("rows").toString());
			} catch (Exception e) {
				throw new Exception("rows转换整数时，出现异常，请确保page类型为整形!");
			}
		}

		if ((null != map.get("page") && null == map.get("rows"))
				|| (null != map.get("rows") && null == map.get("page"))) {
			throw new Exception("请确保page和rows同时传入！");
		}

		if (null != map.get("page") && null != map.get("rows")) {
			pageNo = (pageNo - 1) * pagezize;
			map.put("page", pageNo);
		}

		return map;
	}

	/**
	 *  html标签转义的方法
	 * @param message
	 * @return
	 */
	private static String filter(String message) {
		if (message == null) {
			return (null);
		}
		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				// result.append("");
				break;
			case '>':
				result.append("&gt;");
				// result.append("");
				break;
			// case '&':
			// result.append("&amp;");
			// // result.append("");
			// break;
			case '"':
				result.append("&quot;");
				// result.append("");
				break;
			default:
				result.append(content[i]);
			}
		}
		return (result.toString());
	}

	/**
	 * 获取请求主机IP地址 如果通过代理进来，则透过防火墙获取真实IP地址
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getIpAddress(HttpServletRequest request) {
		if (request == null) {
			return null;
		}

		String ip = request.getHeader("X-Forwarded-For");
		LOGGER.info("X-Forwarded-For - String ip=[{}]", ip);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				LOGGER.info("Proxy-Client-IP - String ip=[{}]", ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				LOGGER.info("WL-Proxy-Client-IP - String ip=[{}]", ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				LOGGER.info("HTTP_CLIENT_IP - String ip=[{}]", ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				LOGGER.info("HTTP_X_FORWARDED_FOR - String ip=[{}]", ip);
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				LOGGER.info("getRemoteAddr - String ip=[{}]", ip);
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}

		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}


	/**
	 * 获取请求主机设备类型 TODO
	 * 
	 * @param request
	 * @return 设备类型【0：IOS、1:：Android、2:：Wap、3:：WeChat、4:：PC】TODO
	 * @throws IOException
	 */
	public static String getDevType(HttpServletRequest request) throws Exception {
		// 设备类型
		Integer devType = 0;
		boolean isMobile = false;
		boolean isIOSDevice = false;
		boolean pcFlag = false;
		boolean mobileFlag = false;
		String via = request.getHeader("Via");
		String userAgent = request.getHeader("user-agent");
		// 判断是否是移动端
		for (int i = 0; via != null && !"".equals(via.trim()) && i < mobileGateWayHeaders.length; i++) {
			if (via.contains(mobileGateWayHeaders[i])) {
				mobileFlag = true;
				break;
			}
		}
		// 判断是否是移动端
		for (int i = 0; !mobileFlag && userAgent != null && !"".equals(userAgent.trim())
				&& i < mobileUserAgents.length; i++) {
			if (userAgent.contains(mobileUserAgents[i])) {
				mobileFlag = true;
				break;
			}
		}

		// 判断是否是ios
		for (int i = 0; !isMobile && userAgent != null && !"".equals(userAgent.trim()) && i < ISO_SYS.length; i++) {
			if (userAgent.contains(ISO_SYS[i])) {
				isIOSDevice = true;
				break;
			}
		}
		for (int i = 0; userAgent != null && !"".equals(userAgent.trim()) && i < pcHeaders.length; i++) {
			if (userAgent.contains(pcHeaders[i])) {
				pcFlag = true;
				break;
			}
		}

		boolean isWeChat = isWeChat(request);
	
		// 判断 是手机 不是pc 不是ios 全为 安卓
		if (mobileFlag == true && pcFlag == false && isIOSDevice == false) {
			return DEV_TYPE_ANDROID;
		}
		if (mobileFlag == true && pcFlag == false && isIOSDevice == true) {
			return DEV_TYPE_IOS;
		}
		if (isWeChat) {
			return DEV_TYPE_WECHAT;
		}
		return DEV_TYPE_PC;
	}

	/**
	 * 判断是否是微信访问
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isWeChat(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent").toLowerCase();
		return userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true;
	}

	private static String DEV_TYPE_ANDROID = "Android";
	private static String DEV_TYPE_IOS = "IOS";
	private static String DEV_TYPE_PC = "PC";
	private static String DEV_TYPE_WECHAT = "WeChat";
	/** Wap网关Via头信息中特有的描述信息 */
	private static String mobileGateWayHeaders[] = new String[] { "ZXWAP", // 中兴提供的wap网关的via信息，例如：Via=ZXWAP
																			// GateWayZTE
																			// Technologies，
			"chinamobile.com", // 中国移动的诺基亚wap网关，例如：Via=WTP/1.1
								// GDSZ-PB-GW003-WAP07.gd.chinamobile.com (Nokia
								// WAP Gateway 4.1 CD1/ECD13_D/4.1.04)
			"monternet.com", // 移动梦网的网关，例如：Via=WTP/1.1
								// BJBJ-PS-WAP1-GW08.bj1.monternet.com. (Nokia
								// WAP Gateway 4.1 CD1/ECD13_E/4.1.05)
			"infoX", // 华为提供的wap网关，例如：Via=HTTP/1.1 GDGZ-PS-GW011-WAP2
						// (infoX-WISG Huawei Technologies)，或Via=infoX WAP
						// Gateway V300R001 Huawei Technologies
			"XMS 724Solutions HTG", // 国外电信运营商的wap网关，不知道是哪一家
			"Bytemobile",// 貌似是一个给移动互联网提供解决方案提高网络运行效率的，例如：Via=1.1 Bytemobile OSN
							// WebProxy/5.1
	};
	private static final String[] ISO_SYS = { "iPhone", "iPad", "iPod" };
	/** 电脑上的IE或Firefox浏览器等的User-Agent关键词 */
	private static String[] pcHeaders = new String[] { "Windows 98", "Windows ME", "Windows 2000", "Windows XP",
			"Windows NT", "Ubuntu" };
	/** 手机浏览器的User-Agent里的关键词 */
	private static String[] mobileUserAgents = new String[] { "Nokia", // 诺基亚，有山寨机也写这个的，总还算是手机，Mozilla/5.0
																		// (Nokia5800
																		// XpressMusic)UC
																		// AppleWebkit(like
																		// Gecko)
																		// Safari/530
			"SAMSUNG", // 三星手机
						// SAMSUNG-GT-B7722/1.0+SHP/VPP/R5+Dolfin/1.5+Nextreaming+SMM-MMS/1.2.0+profile/MIDP-2.1+configuration/CLDC-1.1
			"MIDP-2", // j2me2.0，Mozilla/5.0 (SymbianOS/9.3; U; Series60/3.2
						// NokiaE75-1 /110.48.125 Profile/MIDP-2.1
						// Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML like
						// Gecko) Safari/413
			"CLDC1.1", // M600/MIDP2.0/CLDC1.1/Screen-240X320
			"SymbianOS", // 塞班系统的，
			"MAUI", // MTK山寨机默认ua
			"UNTRUSTED/1.0", // 疑似山寨机的ua，基本可以确定还是手机
			"Windows CE", // Windows CE，Mozilla/4.0 (compatible; MSIE 6.0;
							// Windows CE; IEMobile 7.11)
			"iPhone", // iPhone是否也转wap？不管它，先区分出来再说。Mozilla/5.0 (iPhone; U; CPU
						// iPhone OS 4_1 like Mac OS X; zh-cn) AppleWebKit/532.9
						// (KHTML like Gecko) Mobile/8B117
			"iPad", // iPad的ua，Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X;
					// zh-cn) AppleWebKit/531.21.10 (KHTML like Gecko)
					// Version/4.0.4 Mobile/7B367 Safari/531.21.10
			"Android", // Android是否也转wap？Mozilla/5.0 (Linux; U; Android
						// 2.1-update1; zh-cn; XT800 Build/TITA_M2_16.22.7)
						// AppleWebKit/530.17 (KHTML like Gecko) Version/4.0
						// Mobile Safari/530.17
			"BlackBerry", // BlackBerry8310/2.7.0.106-4.5.0.182
			"UCWEB", // ucweb是否只给wap页面？ Nokia5800
						// XpressMusic/UCWEB7.5.0.66/50/999
			"ucweb", // 小写的ucweb貌似是uc的代理服务器Mozilla/6.0 (compatible; MSIE 6.0;)
						// Opera ucweb-squid
			"BREW", // 很奇怪的ua，例如：REW-Applet/0x20068888 (BREW/3.1.5.20; DeviceId:
					// 40105; Lang: zhcn) ucweb-squid
			"J2ME", // 很奇怪的ua，只有J2ME四个字母
			"YULONG", // 宇龙手机，YULONG-CoolpadN68/10.14 IPANEL/2.0 CTC/1.0
			"YuLong", // 还是宇龙
			"COOLPAD", // 宇龙酷派YL-COOLPADS100/08.10.S100 POLARIS/2.9 CTC/1.0
			"TIANYU", // 天语手机TIANYU-KTOUCH/V209/MIDP2.0/CLDC1.1/Screen-240X320
			"TY-", // 天语，TY-F6229/701116_6215_V0230 JUPITOR/2.2 CTC/1.0
			"K-Touch", // 还是天语K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223
						// Release/30.07.2008 Browser/WAP2.0
			"Haier", // 海尔手机，Haier-HG-M217_CMCC/3.0 Release/12.1.2007
						// Browser/WAP2.0
			"DOPOD", // 多普达手机
			"Lenovo", // 联想手机，Lenovo-P650WG/S100 LMP/LML Release/2010.02.22
						// Profile/MIDP2.0 Configuration/CLDC1.1
			"LENOVO", // 联想手机，比如：LENOVO-P780/176A
			"HUAQIN", // 华勤手机
			"AIGO-", // 爱国者居然也出过手机，AIGO-800C/2.04 TMSS-BROWSER/1.0.0 CTC/1.0
			"CTC/1.0", // 含义不明
			"CTC/2.0", // 含义不明
			"CMCC", // 移动定制手机，K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223
					// Release/30.07.2008 Browser/WAP2.0
			"DAXIAN", // 大显手机DAXIAN X180 UP.Browser/6.2.3.2(GUI) MMP/2.0
			"MOT-", // 摩托罗拉，MOT-MOTOROKRE6/1.0 LinuxOS/2.4.20 Release/8.4.2006
					// Browser/Opera8.00 Profile/MIDP2.0 Configuration/CLDC1.1
					// Software/R533_G_11.10.54R
			"SonyEricsson", // 索爱手机，SonyEricssonP990i/R100 Mozilla/4.0
							// (compatible; MSIE 6.0; Symbian OS; 405) Opera
							// 8.65 [zh-CN]
			"GIONEE", // 金立手机
			"HTC", // HTC手机
			"ZTE", // 中兴手机，ZTE-A211/P109A2V1.0.0/WAP2.0 Profile
			"HUAWEI", // 华为手机，
			"webOS", // palm手机，Mozilla/5.0 (webOS/1.4.5; U; zh-CN)
						// AppleWebKit/532.2 (KHTML like Gecko) Version/1.0
						// Safari/532.2 Pre/1.0
			"GoBrowser", // 3g GoBrowser.User-Agent=Nokia5230/GoBrowser/2.0.290
							// Safari
			"IEMobile", // Windows CE手机自带浏览器，
			"WAP2.0"// 支持wap 2.0的
	};

	/**
	 * 获取CookieValue
	 */
	public static String getCookiesValue(HttpServletRequest request, String key) {
		try {
			LOGGER.info("key：" + key);

			Cookie[] cookies = request.getCookies();
			if (null != cookies && cookies.length > 0) {
				String cookieValue = "";
				for (Cookie cookie : cookies) {
					LOGGER.info("cookieName：" + cookie.getName());
					LOGGER.info("cookieValue：" + cookie.getValue());

					if (key.equals(cookie.getName())) {
						cookieValue = cookie.getValue();
					}
				}
				return cookieValue;
			}
		} catch (Exception e) {
			LOGGER.error("----- RequestUtil.getCookiesValue Exception -----", e);
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 添加数据到Cookie
	 */
	public static void addCookie(HttpServletResponse response, String key, String value) {
		addCookie(response, key, value, null);
	}

	/**
	 * 添加数据到Cookie
	 */
	public static void addCookie(HttpServletResponse response, String key, String value, Integer maxAge) {
		try {
			Cookie cookie = new Cookie(key, value);
			// cookie.setDomain(".1chemic.com"); // cookie作用域
			cookie.setPath("/");
			if (maxAge != null) {
				cookie.setMaxAge(maxAge.intValue());
			}
			response.addCookie(cookie);
		} catch (Exception e) {
			LOGGER.error("----- RequestUtil.addCookie Exception -----", e);
			e.printStackTrace();
		}

		LOGGER.info("AddCookie Succeed key：" + key + "，value：" + value);
	}
	
	/**
	 * 删除Cookie
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
		try {
			LOGGER.info("key：" + key);

			Cookie[] cookies = request.getCookies();
			if (null != cookies && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					LOGGER.info("cookieName：" + cookie.getName());
					LOGGER.info("cookieValue：" + cookie.getValue());

					if (key.equalsIgnoreCase(cookie.getName())) {
						cookie.setValue(null);
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						break;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("----- RequestUtil.getCookiesValue Exception -----", e);
			e.printStackTrace();
		}
	}

	/**
	 * 组装完整的url整句. 包括条件与条件的值，其中排除了若干条件的组装 这个是一个预留接口
	 * 
	 * @param request
	 * @return url整句
	 */
	public static String buildOriginalURL(HttpServletRequest request) {
		StringBuffer originalURL = new StringBuffer();
		originalURL.append(getScheme(request) + "://" + request.getServerName());
		originalURL.append(getServerPort(request));
		originalURL.append(request.getContextPath());
		originalURL.append(request.getServletPath());

		request.getQueryString();
		Map parameters = request.getParameterMap();
		if (parameters != null && parameters.size() > 0) {
			originalURL.append("?");
			for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String[] values = (String[]) parameters.get(key);
				for (int i = 0; i < values.length; i++) {
					originalURL.append(key).append("=").append(values[i]).append("&");
				}
			}
			return originalURL.substring(0, originalURL.length() - 1).toString();
		} else {
			return originalURL.toString();
		}
	}

	/**
	 * 获取DomainURL
	 * 
	 * @param request
	 * @return
	 */
	public static String getDomainURL(HttpServletRequest request) {
		StringBuffer originalURL = new StringBuffer();
		originalURL.append(getScheme(request) + "://" + request.getServerName());
		originalURL.append(getServerPort(request));
		originalURL.append(request.getContextPath());

		return originalURL.toString();
	}

	/**
	 * 获取原始Scheme（代理前真实Scheme）
	 * 
	 * @param request
	 */
	public static String getScheme(HttpServletRequest request) {
		String forwardScheme = request.getHeader("X-Forwarded-Proto");
		LOGGER.info("X-Forwarded-Proto：" + forwardScheme);
		LOGGER.info("request.scheme：" + request.getScheme());

		if (forwardScheme != null && forwardScheme.length() > 0) {
			return forwardScheme;
		}

		return request.getScheme();
	}
	
	/**
	 * 获取端口号
	 * 
	 * @param request
	 */
	public static String getServerPort(HttpServletRequest request) {
		int serverPort = request.getServerPort();
		LOGGER.info("request.serverPort：" + serverPort);

		if (serverPort == 80) {
			return "";
		}

		return ":" + serverPort;
	}

	/**
	 * 是否Ajax请求
	 * 
	 * @param request
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		String ajaxRequest = request.getHeader("Ajax-Request"); // 自定义请求头
		LOGGER.info("X-Requested-With【XMLHttpRequest为Ajax请求】：" + requestType);

		if ("XMLHttpRequest".equalsIgnoreCase(requestType) 
				|| "true".equalsIgnoreCase(ajaxRequest)) {
			return true;
		}

		return false;
	}

}
