package com.zhuanyi.hexo.auth.bo;

import lombok.Data;

@Data
public class TokenBO {

    private final String token;

    private final long expiredTimeStamp;

    public TokenBO(String token, long expiredTimeStamp) {
        this.token = token;
        this.expiredTimeStamp = expiredTimeStamp;
    }
}
