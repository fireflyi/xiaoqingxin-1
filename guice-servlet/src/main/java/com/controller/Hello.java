package com.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.service.HelloService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by lishihao4 on 2019/7/12.
 * DESC TODO
 */
@Singleton
public class Hello extends HttpServlet {

    @Inject
    HelloService helloService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append(helloService.say() + "!!! , Guice-servlet !" + new Date());
    }
}
