package com.zjl.org.config;


import com.zjl.org.Interceptor.LoginInterceptor;
import com.zjl.org.Interceptor.RoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zhoujl
 * @date 2020/3/24
 */
@Configuration
public class IntercepterAdapter implements WebMvcConfigurer {


    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private RoleInterceptor roleInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有的preHandle 执行完再执行全部postHandle 最后是postHandle
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").order(1);
        registry.addInterceptor(roleInterceptor).addPathPatterns("/**").order(2);
    }


}
