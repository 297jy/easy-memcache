package com.zhuanyi.hexo.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhuanyi.hexo.auth.config.SystemConfig;
import com.zhuanyi.hexo.auth.service.AuthService;
import com.zhuanyi.hexo.base.enums.ServiceExceptionEnum;
import com.zhuanyi.hexo.base.exception.ServiceException;
import com.zhuanyi.hexo.base.utils.JsonUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("defaultAuthService")
public class DefaultAuthServiceImpl implements AuthService {

    @Resource
    private SystemConfig systemConfig;

    @Override
    public String getToken() {
        String text = String.format("%s_%s_%s_%s", systemConfig.getUsername(), systemConfig.getPassword(),
                System.currentTimeMillis(), systemConfig.getAuthSecret());
        String token = DigestUtils.md5Hex(text);
        JSONObject data = new JSONObject();
        data.put("token", token);
        data.put("expired_time", System.currentTimeMillis() + systemConfig.getTokenExpireSeconds() * 1000);
        if (JsonUtils.writeJsonObjectToFile(systemConfig.getTokenPath(), data)) {
            return token;
        } else {
            throw new ServiceException(ServiceExceptionEnum.TOKEN_EXCEPTION.getCode(), ServiceExceptionEnum.TOKEN_EXCEPTION.getMessage());
        }
    }

    @Override
    public boolean isValidToken(String token) {
        JSONObject authJsonObject = JsonUtils.readJsonObjectFromFile(systemConfig.getTokenPath());
        String cacheToken = authJsonObject.getString("token");
        if (!token.equals(cacheToken)) {
            return false;
        }
        Long expiredTime = authJsonObject.getLong("expired_time");
        return System.currentTimeMillis() < expiredTime;
    }
}
