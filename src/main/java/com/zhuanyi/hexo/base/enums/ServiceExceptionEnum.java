package com.zhuanyi.hexo.base.enums;


public enum ServiceExceptionEnum {
    TOKEN_EXCEPTION("token_exception", "获取token失败");

    private final String code;

    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ServiceExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
