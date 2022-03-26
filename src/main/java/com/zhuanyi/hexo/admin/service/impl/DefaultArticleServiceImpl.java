package com.zhuanyi.hexo.admin.service.impl;

import com.zhuanyi.hexo.admin.obj.form.ArticleForm;
import com.zhuanyi.hexo.admin.service.ArticleService;
import org.springframework.stereotype.Component;

@Component("defaultArticleService")
public class DefaultArticleServiceImpl implements ArticleService {

    @Override
    public boolean create(ArticleForm articleForm) {
        return false;
    }

}
