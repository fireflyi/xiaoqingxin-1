package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by lishihao
 * @date 2020/4/25
 * DESC TODO
 */

@Controller
@RequestMapping("/api/")
public class Home {

    @RequestMapping(value="order")
    @ResponseBody
    public String save(Integer var1) {

        return "订单"+ var1+"的详细信息.....";
    }
}

