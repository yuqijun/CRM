package com.hwua.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ExtMVCConfig {
    //创建一个实现WebMvcConfigurer接口的对象放到容器中
    @Bean
    public WebMvcConfigurer extWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //注册映射路径到指定的视图
//                registry.addViewController("/").setViewName("login");
//                registry.addViewController("/index.html").setViewName("login");
//                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/pages/user-add.jsp").setViewName("register");
            }
        };
    }
}