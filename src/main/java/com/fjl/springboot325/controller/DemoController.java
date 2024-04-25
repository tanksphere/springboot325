package com.fjl.springboot325.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangjialiang
 * @Description:
 * @date 2024/4/25 22:18
 */
@RestController
public class DemoController {
    @RequestMapping("/demo")
    public String demo(){
        return "hello demo";
    }
}
