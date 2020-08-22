package com.lfy.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by lishihao4 on 2019/2/20.
 * DESC TODO
 */
@Configuration
@MapperScan(basePackages = MasterSqlSessionFactoryConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterSqlSessionFactoryConfig {
    static final String PACKAGE = "com.lfy.dao.master.mapper";

    @Autowired
    @Qualifier("masterDs")
    private DataSource masterDs;

    @Bean
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(masterDs);
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(masterSqlSessionFactory());
        return template;
    }

}
