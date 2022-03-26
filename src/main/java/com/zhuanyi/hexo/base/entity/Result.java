package com.zhuanyi.hexo.base.entity;

import lombok.Getter;

/**
 * 返回结果实体类
 *
 * @author Li Jinhui
 * @update 2019/1/7
 * @since 2018/12/6
 */
@Getter
public class Result {
    private int code;
    private String message;
    private Object data;

    private Result setResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        return this;
    }

    public Result success() {
        return setResult(20000, "Success", null);
    }

    public Result success(Object data) {
        return setResult(20000, "Success", data);
    }

    public Result fail(Object data, String message) {
        return setResult(400, message, data);
    }

    public Result fail(String message) {
        return setResult(400, message, null);
    }

    public Result fail() {
        return setResult(400, "", null);
    }

    public Result fail(Object data, String message, int code) {
        return setResult(code, message, data);
    }

    public Result fail(String message, int code) {
        return setResult(code, message, null);
    }
}