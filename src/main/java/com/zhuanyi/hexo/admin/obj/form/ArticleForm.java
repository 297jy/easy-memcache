package com.zhuanyi.hexo.admin.obj.form;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleForm {

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
}
