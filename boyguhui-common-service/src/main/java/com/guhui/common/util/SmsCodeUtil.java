package com.guhui.common.util;

import org.sagacity.sqltoy.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/4/29$ 15:29$
 */
public class SmsCodeUtil {

	public static void main(String[] args){
		HashMap map = new HashMap();
		map.put("code","123");
		map.put("name","guhui");
		String message = "你好:${name},你的验证码为${code}";
		String newMessage = replaceMessageTemplateContents(map,message);
		//你好:guhui,你的验证码为123
		System.out.println(newMessage);
	}

	/**
	 * 替换消息模版中参数，拼接为完整的消息信息
	 *
	 * @param paramMap
	 * @param templateContents
	 * @return
	 */
	private static String replaceMessageTemplateContents(HashMap<String, String> paramMap, String templateContents) {
		String userMessageContents = templateContents;
		if (StringUtil.isNotBlank(paramMap)) {
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				userMessageContents = userMessageContents.replace("${".concat(entry.getKey()).concat("}"),
						entry.getValue() == null ? " " : entry.getValue());
			}
		}
		return userMessageContents;
	}

}
