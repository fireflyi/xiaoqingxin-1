package com.test;


import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.test.aop.GerantMethodInterceptor;
import com.test.aop.Gnamed;
import com.test.util.PropertyUtil;

/**
 * Created by lishihao4 on 2019/7/12.
 * DESC TODO
 */

public class A {
    @Inject
    private UserInfoService userInfoService;

    public static void main(String[] a){
//        Injector injector = Guice.createInjector();
//        A ac = injector.getInstance(A.class);
//        System.out.println(ac.userInfoService.getInfo());
//        System.out.println(ac.userInfoService.getPro());

        final String file = "guice.properties";

        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                Names.bindProperties(binder, PropertyUtil.loadFile(file, getClass()));
                binder.bindInterceptor(Matchers.any(),
                        Matchers.annotatedWith(Gnamed.class),
                        new GerantMethodInterceptor());
            }
        });
        UserInfoService u = injector.getInstance(A.class).userInfoService;
        String res = u.getPro();
        System.out.println(res);

    }
}
