package com.guhui.idempotence.service.impl;

import com.guhui.idempotence.service.TokenService;
import com.guhui.idempotence.util.MapUtil;
import com.guhui.order.api.entity.BizResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Project boyguhui-parent
 * @Description: Token接口实现类
 * @Author: guhui
 * @Date: 2019/8/15
 */
@Service
public class TokenServiceImpl implements TokenService {

	private static final String TOKEN_NAME = "TOKEN";

	private static final String TOKEN_KEY = "BOYGUHUI";

	@Override
	public BizResult<String> createToken() {
		try {
			String str = UUID.randomUUID().toString();
			//token的key
			StringBuilder key = new StringBuilder().append(TOKEN_KEY).append(str);
			//token的value
			StringBuilder value = new StringBuilder().append(TOKEN_NAME).append(str);

			//map里面保存一份Token
			MapUtil.getMap().put(key.toString(),value.toString());
			return BizResult.success(key.toString());
		}catch (Exception e){
			return BizResult.error();
		}
	}

	@Override
	public Boolean checkToken(HttpServletRequest request) {
		String token = request.getHeader("TOKEN");
		//判断token是否为空
		if (StringUtils.isEmpty(token)){
			return false;
		}
		// 判断token是否存在，不存在返回false，退回请求
		if (!MapUtil.getMap().containsKey(token)){
			return false;
		}
		// 删除map中保存的Token result等于null代表token没找到
		String result = MapUtil.getMap().remove(token);
		if (StringUtils.isEmpty(result)){
			return false;
		}
		return true;
	}
}
