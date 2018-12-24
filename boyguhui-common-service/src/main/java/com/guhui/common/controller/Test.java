package com.guhui.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guhui ^-^ on 2018/12/7.
 */
@RestController
public class Test {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
