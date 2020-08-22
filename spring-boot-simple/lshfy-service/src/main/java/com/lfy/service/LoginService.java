package com.lfy.service;

import com.lfy.domain.User;

/**
 * Created by lishihao4 on 2019/3/4.
 * DESC TODO
 */
public interface LoginService {

    void setSession(String key, User user);

    User getSession(String key);

}
