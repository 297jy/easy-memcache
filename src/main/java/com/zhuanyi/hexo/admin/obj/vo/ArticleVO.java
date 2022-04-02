package com.zhuanyi.hexo.admin.obj.vo;

import com.zhuanyi.hexo.admin.obj.pojo.Article;
import com.zhuanyi.hexo.base.utils.DateUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Date;


@Data
public class ArticleVO {

    private Integer no;

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

    public ArticleVO() {
    }

    public ArticleVO(Article article) {
        BeanUtils.copyProperties(article, this);
        publishTime = DateUtils.toDate(article.getPublishTime(), DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS);
        if (!CollectionUtils.isEmpty(article.getCategories())) {
            categories = StringUtils.join(article.getCategories(), ",");
        }
        if (!CollectionUtils.isEmpty(article.getTags())) {
            tags = StringUtils.join(article.getTags(), ",");
        }
    }
}
