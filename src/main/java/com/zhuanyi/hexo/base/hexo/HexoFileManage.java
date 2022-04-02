package com.zhuanyi.hexo.base.hexo;

import com.zhuanyi.hexo.admin.obj.pojo.Article;

import java.util.List;

public interface HexoFileManage {

    List<Article> readAllArticles();

    Article readArticleById(Long id);

    boolean saveArticle(Article article);

    boolean deleteArticleById(Long id);

    Long getNewArticleId();

}
