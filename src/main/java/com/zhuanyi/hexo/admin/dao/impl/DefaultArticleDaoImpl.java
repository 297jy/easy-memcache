package com.zhuanyi.hexo.admin.dao.impl;

import com.zhuanyi.hexo.admin.dao.ArticleDao;
import com.zhuanyi.hexo.admin.obj.pojo.Article;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("defaultArticleDao")
public class DefaultArticleDaoImpl implements ArticleDao {

    @Override
    public Article findArticleByTitle(String title) {
        return null;
    }

    @Override
    public List<Article> findAllArticles() {
        return null;
    }

    @Override
    public boolean saveArticle(Article article) {
        return false;
    }

    @Override
    public boolean updateArticleByTitle(Article article, String title) {
        return false;
    }

    @Override
    public boolean deleteArticleByTitle(String title) {
        return false;
    }

}
