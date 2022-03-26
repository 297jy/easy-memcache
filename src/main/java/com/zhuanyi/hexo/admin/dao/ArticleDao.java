package com.zhuanyi.hexo.admin.dao;

import com.zhuanyi.hexo.admin.obj.pojo.Article;

import java.util.List;

public interface ArticleDao {

    Article findArticleByTitle(String title);

    List<Article> findAllArticles();

    boolean saveArticle(Article article);

    boolean updateArticleByTitle(Article article, String title);

    boolean deleteArticleByTitle(String title);

}
