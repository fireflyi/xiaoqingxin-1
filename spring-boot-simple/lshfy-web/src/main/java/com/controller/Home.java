package com.controller;

import com.lfy.domain.Tab;
import com.lfy.domain.User;
import com.lfy.entity.Res;
import com.lfy.service.TabService;
import com.lfy.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lishihao4 on 2019/1/11.
 * DESC TODO
 */
@Controller
@RequestMapping("/home/")
public class Home {

    @Resource
    UserService userService;

    @Resource
    TabService tabService;

    @Value("${fy}")
    private String a;

    @RequestMapping(value="hello")
    @ResponseBody
    public String index() {
        return "Hello World"+a;
    }

    @RequestMapping("mybatis")
    @ResponseBody
    public Res mybatis() {
        Res res = new Res();
        List<User> r = userService.list();
        res.setData(r);
        return res;
    }

    @RequestMapping("mybatis2")
    @ResponseBody
    public Res mybatis2() {
        Res res = new Res();
        List<Tab> r = tabService.list();
        res.setData(r);
        return res;
    }

    @RequestMapping("freeMak")
    public String freeMak(Map<String, Object> model) {
        model.put("a","sadada");
        return "index";
    }


}
