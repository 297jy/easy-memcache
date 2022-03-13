package com.zhuanyi.hexo.auth.config;

import com.zhuanyi.hexo.auth.interceptor.AuthValidateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Resource
    private AuthValidateInterceptor authValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(authValidateInterceptor);
        registration.addPathPatterns("/**");                      //所有路径都被拦截
    }
}
