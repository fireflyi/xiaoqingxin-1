package com.controller.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by lishihao4 on 2019/3/4.
 * DESC TODO
 */
@Component
public class LfyConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new UserInfoInterceptor())
                                    .addPathPatterns("/**")
                                    .excludePathPatterns("/static/**","/login/**");

    }

}
