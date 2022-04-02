package com.zhuanyi.hexo.admin.obj.form;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleForm {

    private Long id;

    //文章封面图片
    private String cover;

    private String categories;

    private String tags;

    private String content;

    private String describe;

    private Date publishTime;

    private String status;

    private String title;

    //关键词
    private String keyWords;

    private String author;
}
