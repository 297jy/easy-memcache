package com.zhuanyi.hexo.admin.obj.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleListVO {

    private List<ArticleVO> items;

    private int total;

    public ArticleListVO(List<ArticleVO> articleVOS, int total) {
        this.items = articleVOS;
        this.total = total;
    }
}
