package com;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

/**
 * @author by lishihao
 * @date 2019/10/16
 * DESC TODO
 */
public class WsLaunches {

    @Inject
    SocketApplication socketApplication;

    public static void main(String[] a){

        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                //Names.bindProperties(binder, PropertyUtil.loadFile(file, getClass()));

                //binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Gnamed.class), new GerantMethodInterceptor());
            }
        });
        SocketApplication gss = injector.getInstance(WsLaunches.class).socketApplication;

    }

}
