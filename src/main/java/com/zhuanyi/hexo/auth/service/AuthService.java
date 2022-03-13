package com.zhuanyi.hexo.auth.service;

public interface AuthService {

    boolean checkAccountInfo(String username, String password);

    String generateToken();

    boolean isValidToken(String token);

    boolean clearToken();

}
