package com.lfy.service;

import com.lfy.domain.User;

import java.util.List;

/**
 * Created by lishihao4 on 2019/1/16.
 * DESC TODO
 */
public interface UserService {


    List<User> list();

    User ckLogin(User u);

}
