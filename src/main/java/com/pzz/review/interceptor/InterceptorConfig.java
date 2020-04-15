package com.pzz.review.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //用户需登陆访问的接口
//        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/**", "/project/**", "/attachment/**", "/logout");
    }
}
