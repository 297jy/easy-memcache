package com.zhuanyi.hexo.admin.obj.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Article {

    //文章封面图片
    private String cover;

    private String author;

    private List<String> categories;

    private List<String> tags;

    private String content;

    private String describe;

    private String publishTime;

    private String status;

    private String title;

    //关键词
    private String keyWords;

}
