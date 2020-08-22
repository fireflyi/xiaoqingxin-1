package com.lfy.service.impl;

import com.lfy.domain.User;
import com.lfy.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * Created by lishihao4 on 2019/3/4.
 * DESC TODO
 */
@Service("EhCacheLoginService")
class EhCacheLoginServiceImpl implements LoginService {
    @Override
    public void setSession(String key, User user) {

    }

    @Override
    public User getSession(String key) {
        return null;
    }
}
