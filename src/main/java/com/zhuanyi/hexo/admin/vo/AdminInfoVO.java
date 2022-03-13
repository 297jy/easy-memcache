package com.zhuanyi.hexo.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class AdminInfoVO {

    private String avatar;

    private String introduction;

    private String name;

    private List<String> roles;

}
