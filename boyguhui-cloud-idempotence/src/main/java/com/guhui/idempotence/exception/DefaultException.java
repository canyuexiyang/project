package com.guhui.idempotence.exception;

/**
 * 自定义异常，继承RuntimeException
 */
public class DefaultException extends RuntimeException{


    public DefaultException(String message) {
        super(message);
    }

}
