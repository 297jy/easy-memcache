package com.zhuanyi.hexo.admin.dao.impl;

import com.zhuanyi.hexo.admin.config.SystemConfig;
import com.zhuanyi.hexo.admin.dao.ArticleDao;
import com.zhuanyi.hexo.admin.obj.pojo.Article;
import com.zhuanyi.hexo.base.hexo.HexoFileManage;
import com.zhuanyi.hexo.base.utils.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("defaultArticleDao")
public class DefaultArticleDaoImpl implements ArticleDao {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private HexoFileManage defaultHexoFileManage;

    @Override
    public Article findArticleByTitle(String title) {
        return defaultHexoFileManage.readArticle(title);
    }

    @Override
    public List<Article> findAllArticles() {
        List<String> files = FileUtils.getFiles(systemConfig.getHexoSourcePath());

        List<Article> articles = new ArrayList<>();
        for (String file : files) {
            articles.add(defaultHexoFileManage.readArticle(file));
        }
        return articles;
    }

    @Override
    public boolean saveArticle(Article article) {
        return defaultHexoFileManage.saveArticle(article);
    }

    @Override
    public boolean updateArticleByTitle(Article article, String title) {
        return defaultHexoFileManage.saveArticle(article);
    }

    @Override
    public boolean deleteArticleByTitle(String title) {
        return defaultHexoFileManage.deleteArticle(title);
    }

}
