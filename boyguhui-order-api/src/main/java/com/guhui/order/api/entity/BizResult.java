package com.guhui.order.api.entity;

import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 业务结果返回
 * 
 * @author wen.zhang
 */
public class BizResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 【Biz】处理状态【1:成功、0：失败】 */
	private ResultEnum result;
	/** 【Biz】返回消息 */
	private String msg;
	/** 【Biz】返回数据 */
	private T data;
	/** 【分页】数据总件数 */
	private Long count;

	public BizResult() {
	}

	public BizResult(T data) {
		this(ResultEnum.SUCCESS, data, null);
	}

	public BizResult(ResultEnum result) {
		this(result, null, null);
	}

	public BizResult(ResultEnum result, String msg) {
		this(result, null, msg);
	}

	public BizResult(ResultEnum result, T data, String msg) {
		Assert.notNull(result, "ResultEnum must not be null");
		this.result = result;
		this.data = data;
		this.msg = msg;
	}

	public BizResult(ResultEnum result, T data, String msg, Long count) {
		Assert.notNull(result, "ResultEnum must not be null");
		this.result = result;
		this.data = data;
		this.msg = msg;
		this.count = count;
	}

	/**
	 * 返回成功状态
	 */
	public static <T> BizResult<T> success() {
		return new BizResult<T>(ResultEnum.SUCCESS);
	}

	/**
	 * 返回成功状态和业务数据
	 */
	public static <T> BizResult<T> success(T data) {
		return new BizResult<T>(data);
	}

	/**
	 * 返回成功状态、业务数据和总件数
	 */
	public static <T> BizResult<T> success(T data, long count) {
		return new BizResult<T>(ResultEnum.SUCCESS, data, null, count);
	}

	/**
	 * 返回失败状态
	 */
	public static <T> BizResult<T> error() {
		return new BizResult<T>(ResultEnum.ERROR);
	}

	/**
	 * 返回失败状态和错误消息
	 */
	public static <T> BizResult<T> error(String msg) {
		return new BizResult<T>(ResultEnum.ERROR, msg);
	}

	public int getResultCode() {
		return this.result.getCode();
	}

	/**
	 * 判断是否成功
	 */
	public boolean isSuccess() {
		return ResultEnum.SUCCESS.equals(this.result);
	}

	/**
	 * 判断是否返回了业务数据
	 */
	public boolean hasData() {
		return (this.data != null);
	}

	public ResultEnum getResult() {
		return this.result;
	}

	public void setResult(ResultEnum result) {
		this.result = result;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "BizResult [result=" + result + ", msg=" + msg + ", data=" + data + ", count=" + count + "]";
	}

}
