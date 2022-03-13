package com.zhuanyi.hexo.auth.dao;

import com.zhuanyi.hexo.auth.bo.TokenBO;

public interface TokenDao {

    TokenBO getToken();

    boolean updateToken(TokenBO tokenBO);

}
