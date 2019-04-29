package com.guhui.order.api.entity;



import org.sagacity.sqltoy.utils.StringUtil;

import java.io.Serializable;

public class Error implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误代码
     */
    private String code = "9999";
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 错误对象Key
     */
    private String key;

    public Error() {
    }

    public Error(String code, String msg, String key) {
        if (StringUtil.isNotBlank(code)) {
            this.code = code;
        }
        this.msg = msg;
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Error [code=" + code + ", msg=" + msg + ", key=" + key + "]";
    }


}
