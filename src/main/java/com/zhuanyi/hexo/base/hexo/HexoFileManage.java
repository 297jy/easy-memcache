package com.zhuanyi.hexo.base.hexo;

import com.zhuanyi.hexo.admin.obj.pojo.Article;

import java.util.List;

public interface HexoFileManage {

    List<Article> readAllArticles();

    List<Article> readAllTmpArticles();

    Article readArticleById(Long id);

    Article readTmpArticleById(Long id);

    boolean saveArticle(Article article);

    boolean tmpSaveArticle(Article article);

    boolean deleteArticleById(Long id);

    boolean deleteTmpArticleById(Long id);

    Long getNewArticleId();

}
