package com.servlet;

import com.controller.Hello;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

/**
 * Created by lishihao4 on 2019/7/12.
 * DESC TODO
 */
public class GuiceBootstrapServletConfigListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule(){
            @Override
            protected void configureServlets() {
                serve("/Hello").with(Hello.class);
            }
        });
    }
}
