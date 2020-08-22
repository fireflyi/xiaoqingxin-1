package com.lfy.service.impl;

import com.lfy.dao.master.mapper.UserMapper;
import com.lfy.domain.User;
import com.lfy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lishihao4 on 2019/1/16.
 * DESC TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User ckLogin(User u) {
        return null;
    }
}
