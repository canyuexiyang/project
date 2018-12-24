package com.guhui.common.entity;

/**
 * @author xuan <a href="zhangshixuanj@163.com">联系作者</a>
 * @version Revision:v1.0,Date: 2018/1/11 13:21
 * @project sinochem-parent
 * @description 返回
 * @Modification Date: 2018/1/11 13:21 {填写修改说明}
 */
public enum ResultEnum {
	SUCCESS(1), ERROR(0);

	private int code;

	ResultEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
