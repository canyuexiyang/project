package com.guhui.idempotence.exception;

import com.guhui.order.api.entity.BizResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by guhui ^-^ on 2018/3/29.
 * 使用ControllerAdvice增强控制器
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {


    /**
     * 配置全局异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BizResult defaultErrorHandler(Exception e){
        //判断异常类型
        if (e instanceof DefaultException){
            DefaultException defaultException = (DefaultException) e;
            return BizResult.error(defaultException.getMessage());
        }else{
            return BizResult.error();
        }


    }

}
