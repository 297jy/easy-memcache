package com.zhuanyi.hexo.auth.service.impl;

import com.zhuanyi.hexo.auth.bo.TokenBO;
import com.zhuanyi.hexo.auth.config.SystemConfig;
import com.zhuanyi.hexo.auth.dao.TokenDao;
import com.zhuanyi.hexo.auth.service.AuthService;
import com.zhuanyi.hexo.base.enums.ServiceExceptionEnum;
import com.zhuanyi.hexo.base.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("defaultAuthService")
public class DefaultAuthServiceImpl implements AuthService {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private TokenDao defaultTokenDao;

    @Override
    public boolean checkAccountInfo(String username, String password) {

        return systemConfig.getUsername().equals(username) && systemConfig.getPassword().equals(password);

    }

    @Override
    public String generateToken() {
        String text = String.format("%s_%s_%s_%s", systemConfig.getUsername(), systemConfig.getPassword(),
                System.currentTimeMillis(), systemConfig.getAuthSecret());
        String token = DigestUtils.md5Hex(text);
        long expiredTime = System.currentTimeMillis() + systemConfig.getTokenExpireSeconds() * 1000;
        TokenBO tokenBO = new TokenBO(token, expiredTime);
        if (defaultTokenDao.updateToken(tokenBO)) {
            return token;
        } else {
            throw new ServiceException(ServiceExceptionEnum.TOKEN_EXCEPTION.getCode(), ServiceExceptionEnum.TOKEN_EXCEPTION.getMessage());
        }
    }

    @Override
    public boolean isValidToken(String token) {
        TokenBO tokenBO = defaultTokenDao.getToken();
        if (!token.equals(tokenBO.getToken())) {
            return false;
        }
        return System.currentTimeMillis() < tokenBO.getExpiredTimeStamp();
    }

    @Override
    public boolean clearToken() {
        return defaultTokenDao.updateToken(new TokenBO("", 0));
    }
}
