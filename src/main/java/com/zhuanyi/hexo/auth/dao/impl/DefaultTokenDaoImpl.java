package com.zhuanyi.hexo.auth.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhuanyi.hexo.auth.bo.TokenBO;
import com.zhuanyi.hexo.auth.config.SystemConfig;
import com.zhuanyi.hexo.auth.dao.TokenDao;
import com.zhuanyi.hexo.base.utils.JsonUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("defaultTokenDao")
public class DefaultTokenDaoImpl implements TokenDao {

    @Resource
    private SystemConfig systemConfig;

    @Override
    public TokenBO getToken() {
        JSONObject authJsonObject = JsonUtils.readJsonObjectFromFile(systemConfig.getTokenPath());
        return new TokenBO(authJsonObject.getString("token"), authJsonObject.getLong("expired_time"));
    }

    @Override
    public boolean updateToken(TokenBO tokenBO) {
        JSONObject data = new JSONObject();
        data.put("token", tokenBO.getToken());
        data.put("expired_time", tokenBO.getExpiredTimeStamp());
        return JsonUtils.writeJsonObjectToFile(systemConfig.getTokenPath(), data);
    }
}
