package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by lishihao4 on 2019/1/11.
 * DESC TODO
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
//@MapperScan("com.com.lfy.com.lfy.dao.master.mapper") 配置了多数据源这里不需要
//@PropertySource(value = {"classpath:/config/${env}/fy.properties"},encoding = "utf-8")
public class LshFyApp {
    public static void main(String[] args) {
        SpringApplication.run(LshFyApp.class, args);
    }

}
