package com.service;

import com.google.inject.Singleton;

/**
 * Created by lishihao4 on 2019/7/17.
 * DESC TODO
 */
@Singleton
public class HelloServiceImpl implements HelloService {

    @Override
    public String say() {
        return "hello";
    }
}
