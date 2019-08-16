package com.guhui.idempotence.controller;

import com.guhui.idempotence.service.TokenService;
import com.guhui.order.api.entity.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project boyguhui-parent
 * @Description: Token Controller
 * @Author: guhui
 * @Date: 2019/8/15
 */
@RestController
public class TokenController {

	@Autowired
	private TokenService tokenService;

	/**
	 * 创建Token
	 * @return
	 */
	@GetMapping("crateToken")
	public BizResult<String> createToken(){
		return this.tokenService.createToken();
	}

}
