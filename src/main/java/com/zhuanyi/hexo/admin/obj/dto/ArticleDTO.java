package com.zhuanyi.hexo.admin.obj.dto;

import com.zhuanyi.hexo.admin.obj.form.ArticleForm;
import com.zhuanyi.hexo.admin.obj.pojo.Article;
import com.zhuanyi.hexo.base.utils.DateUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Date;


public class ArticleDTO extends Article {


    public ArticleDTO() {
    }

    public ArticleDTO(ArticleForm articleForm) {
        BeanUtils.copyProperties(articleForm, this);
        setCategories(Arrays.asList(articleForm.getCategories().split(",")));
        setTags(Arrays.asList(articleForm.getCategories().split(",")));
        if (articleForm.getPublishTime() == null) {
            articleForm.setPublishTime(new Date());
        }
        setPublishTime(DateUtils.format(articleForm.getPublishTime(), DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS));
    }
}
