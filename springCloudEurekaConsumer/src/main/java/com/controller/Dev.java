package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author by lishihao
 * @date 2020/4/25
 * DESC TODO
 */
@RestController
public class Dev {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/dev1")
    public String dev1(Integer orderId) {
        return restTemplate.getForEntity("http://ORDER-SERVICE/api/order?var1="+orderId, String.class).getBody();
    }

}
