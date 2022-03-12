package com.zhuanyi.hexo.auth.interceptor;

import com.zhuanyi.hexo.auth.annotation.Auth;
import com.zhuanyi.hexo.auth.service.AuthService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthValidateInterceptor implements HandlerInterceptor {

    @Resource
    private AuthService defaultAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Auth authAnnotation = method.getAnnotation(Auth.class);
        if (authAnnotation != null) {
            if (authAnnotation.required()) {
                String token = request.getHeader("X-Token");
                return defaultAuthService.isValidToken(token);
            }
        }
        return true;
    }
}
