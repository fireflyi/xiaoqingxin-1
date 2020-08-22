package com.test.gouzao;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.test.UserInfoService;
import com.test.aop.GerantMethodInterceptor;
import com.test.aop.Gnamed;
import com.test.util.PropertyUtil;

/**
 * @author by fireflyi (6025606@qq.com)
 * @website https://www.fireflyi.com
 * @date 2019/8/9
 * DESC TODO
 */
public class A {
//    @Inject
//    private String sss = "aaa";
//
//    public static void main(String[] a){
////        Injector injector = Guice.createInjector();
////        A ac = injector.getInstance(A.class);
////        System.out.println(ac.userInfoService.getInfo());
////        System.out.println(ac.userInfoService.getPro());
//
//        final String file = "guice.properties";
//
//        Injector injector = Guice.createInjector(new Module() {
//            @Override
//            public void configure(Binder binder) {
//                Names.bindProperties(binder, PropertyUtil.loadFile(file, getClass()));
//                binder.bindInterceptor(Matchers.any(),
//                        Matchers.annotatedWith(Gnamed.class),
//                        new GerantMethodInterceptor());
//            }
//        });
//        UserInfoService u = injector.getInstance(com.test.A.class).userInfoService;
//        String res = u.getPro();
//        System.out.println(res);
//
//    }
}
