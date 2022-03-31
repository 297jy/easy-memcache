package com.zhuanyi.hexo.admin.obj.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Article implements Comparable<Article>{

    //文章封面图片
    private String cover;

    private List<String> categories;

    private List<String> tags;

    private String content;

    private String describe;

    private String publishTime;

    private String status;

    private String title;

    //关键词
    private String keyWords;

    @Override
    public String toString() {
        return "Article{" +
                "cover='" + cover + '\'' +
                ", categories=" + categories +
                ", tags=" + tags +
                ", content='" + content + '\'' +
                ", describe='" + describe + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", keyWords='" + keyWords + '\'' +
                '}';
    }

    @Override
    public int compareTo(Article o) {
        return publishTime.compareTo(o.getPublishTime());
    }
}
