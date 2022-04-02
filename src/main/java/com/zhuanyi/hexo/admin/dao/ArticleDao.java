package com.zhuanyi.hexo.admin.dao;

import com.zhuanyi.hexo.admin.obj.pojo.Article;

import java.util.List;

public interface ArticleDao {

    Article findArticleById(Long id);

    List<Article> findAllArticles();

    boolean saveArticle(Article article);

    boolean updateArticle(Article article);

    boolean deleteArticleById(Long id);

}
