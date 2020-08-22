package com.test;

import com.google.inject.ImplementedBy;

/**
 * Created by lishihao4 on 2019/7/12.
 * DESC TODO
 */
@ImplementedBy(UserInfoServiceImpl.class)
public interface UserInfoService {

     String getInfo();

     String getPro();
}
