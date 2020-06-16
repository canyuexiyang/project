package com.guhui.idempotence.service;

import com.guhui.order.api.entity.BizResult;;
import javax.servlet.http.HttpServletRequest;

/**
 * @Project boyguhui-parent
 * @Description: Token 接口
 * @Author: guhui
 * @Date: 2019/8/15
 */
public interface TokenService {

	BizResult<String> createToken();

	Boolean checkToken(HttpServletRequest request);

}
