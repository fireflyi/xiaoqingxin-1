package com.test;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.test.aop.Gnamed;

/**
 * Created by lishihao4 on 2019/7/12.
 * DESC TODO
 */
@Singleton
public class UserInfoServiceImpl implements UserInfoService{

    @Inject
    @Named("guice.xqx.ip")
    private String ip;

    @Inject
    @Named("guice.xqx.port")
    private String port;


    @Override
    public String getInfo() {
        return "lsh";
    }

    @Gnamed
    @Override
    public String getPro(){
        return "ip->"+ip+",port->"+port;
    }
}
