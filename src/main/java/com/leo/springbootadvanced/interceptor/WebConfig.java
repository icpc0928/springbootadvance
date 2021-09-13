package com.leo.springbootadvanced.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    //註冊攔截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //new 我們實現的攔截器 .addPathPatterns要攔截的地址 .excludePathPatterns("")排除不須攔截的
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/")
                .addPathPatterns("/books/**");
    }
}
