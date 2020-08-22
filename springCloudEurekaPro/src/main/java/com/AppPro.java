package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Eureka注册中心
 * Created by lishihao4 on 2019/1/11.
 * DESC TODO
 */
@EnableEurekaClient
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class AppPro {
    public static void main(String[] args) {
        SpringApplication.run(AppPro.class, args);
    }

}
