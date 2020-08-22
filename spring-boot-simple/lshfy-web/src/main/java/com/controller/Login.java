package com.controller;

import com.lfy.domain.User;
import com.lfy.entity.Res;
import com.lfy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lishihao4 on 2019/3/4.
 * DESC TODO
 */
@Controller
@RequestMapping("/login/")
public class Login {

    @Resource
    UserService userService;

    @RequestMapping("cklogin")
    @ResponseBody
    public Res cklogin(User u) {
        Res res = new Res();
        try{
            User r = userService.ckLogin(u);
            res.setSuccess(r);
        } catch (Exception e) {
            res.setFail(e.getMessage());
        }
        return res;
    }

    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer=null;
        try{
            URL url=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            //往服务器端写内容 也就是发起http请求需要带的参数
            if(null!=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String[] a){
        //Login.b();
    }



}
