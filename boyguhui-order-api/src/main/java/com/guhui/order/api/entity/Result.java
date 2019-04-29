package com.guhui.order.api.entity;

import java.io.Serializable;


public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 处理状态（1:成功、0：失败）
     */
    private int success;
    /**
     * 错误消息
     */
    private Error error = new Error();
    /**
     * 业务数据
     */
    private T data;

    public Result() {
    }

    /**
     * 成功
     *
     * @param data
     */
    public Result(T data) {
        this.success = ResultEnum.SUCCESS.getCode();
        this.data = data;
        if (this.data == null) {
            this.data = (T) "";
        }
        error = null;
    }

    /**
     * 失败
     *
     * @param error
     */
    public Result(Error error) {
        this.success = ResultEnum.ERROR.getCode();
        this.error.setCode(error.getCode());
        this.error.setMsg(error.getMsg());
        this.error.setKey(error.getKey());
    }

    public int getSuccess() {
        return success;
    }

    public Error getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public void setSuceess(int success) {
        this.success = success;
    }

    public void setErrorCode(String errorCode) {
        this.error.setCode(errorCode);
    }

    public void setErrorMsg(String errorMsg) {
        this.error.setMsg(errorMsg);
    }

    public void setErrorKey(String errorKey) {
        this.error.setKey(errorKey);
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [success=" + success + ", error=" + error + ", data="
                + data + "]";
    }

	public void setSuccess(int success) {
		this.success = success;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public Result(int success, Error error, T data) {
		super();
		this.success = success;
		this.error = error;
		this.data = data;
	}

	/**
	 * 返回成功状态
	 */
	public static <T> Result<T> success() {
		return new Result<T>(ResultEnum.SUCCESS.getCode(), null, null);
	}

	/**
	 * 返回成功状态和业务数据
	 */
	public static <T> Result<T> success(T data) {
		return new Result<T>(ResultEnum.SUCCESS.getCode(), null, data);
	}

	/**
	 * 返回失败状态
	 */
	public static <T> Result<T> error() {
		return new Result<T>(ResultEnum.ERROR.getCode(), null, null);
	}

	/**
	 * 返回失败状态和错误消息
	 */
	public static <T> Result<T> error(String msg) {
		Error errorObj = new Error();
		errorObj.setMsg(msg);
		return new Result<T>(ResultEnum.ERROR.getCode(), errorObj, null);
	}

}
