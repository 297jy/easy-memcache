package com.zhuanyi.hexo.auth.config;

import com.alibaba.fastjson.JSONObject;
import com.zhuanyi.hexo.base.constant.SystemConfigConstant;
import com.zhuanyi.hexo.base.utils.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Data
@Slf4j
public class SystemConfig {

    private String username;

    private String password;

    private String tokenPath;

    private String authSecret;

    private Long tokenExpireSeconds;

    private String avatar;

    private String introduction;

    private String hexoPath;

    @PostConstruct
    private void init() {
        JSONObject configJsonObject = JsonUtils.readJsonObjectFromFile(SystemConfigConstant.SYSTEM_CONFIG_PATH);
        username = configJsonObject.getString(SystemConfigConstant.USERNAME_KEY);
        password = configJsonObject.getString(SystemConfigConstant.PASSWORD_KEY);
        tokenPath = configJsonObject.getString(SystemConfigConstant.TOKEN_PATH_KEY);
        authSecret = configJsonObject.getString(SystemConfigConstant.AUTH_SECRET_KEY);
        tokenExpireSeconds = configJsonObject.getLong(SystemConfigConstant.TOKEN_EXPIRE_SECONDS_KEY);
        avatar = configJsonObject.getString(SystemConfigConstant.AVATAR_KEY);
        introduction = configJsonObject.getString(SystemConfigConstant.INTRODUCTION_KEY);
        hexoPath = configJsonObject.getString(SystemConfigConstant.HEXO_PATH_KEY);
    }
}
