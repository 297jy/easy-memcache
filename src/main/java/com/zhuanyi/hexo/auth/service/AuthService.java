package com.zhuanyi.hexo.auth.service;

public interface AuthService {

    String getToken();

    boolean isValidToken(String token);

}
