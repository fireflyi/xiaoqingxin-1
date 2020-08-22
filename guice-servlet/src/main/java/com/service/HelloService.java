package com.service;

import com.google.inject.ImplementedBy;

/**
 * Created by lishihao4 on 2019/7/17.
 * DESC TODO
 */
@ImplementedBy(HelloServiceImpl.class)
public interface HelloService {

    String say();
}
