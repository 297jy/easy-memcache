package com.zhuanyi.hexo.admin.dao;

import com.zhuanyi.hexo.admin.obj.pojo.Article;

import java.util.List;

public interface ArticleDao {

    Article findArticleById(Long id);

    Article findTmpArticleById(Long id);

    List<Article> findAllArticles();

    List<Article> findAllTmpArticles();

    boolean saveArticle(Article article);

    boolean tmpSaveArticle(Article article);

    boolean updateArticle(Article article);

    boolean deleteArticleById(Long id);

    boolean deleteTmpArticleById(Long id);

}
