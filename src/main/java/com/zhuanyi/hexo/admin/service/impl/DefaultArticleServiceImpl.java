package com.zhuanyi.hexo.admin.service.impl;

import com.zhuanyi.hexo.admin.dao.ArticleDao;
import com.zhuanyi.hexo.admin.obj.dto.ArticleDTO;
import com.zhuanyi.hexo.admin.obj.form.ArticleForm;
import com.zhuanyi.hexo.admin.obj.pojo.Article;
import com.zhuanyi.hexo.admin.obj.vo.ArticleVO;
import com.zhuanyi.hexo.admin.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("defaultArticleService")
public class DefaultArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao defaultArticleDao;

    @Override
    public List<ArticleVO> findAllArticles(Integer page, Integer limit) {
        List<Article> articles = defaultArticleDao.findAllArticles();
        return paging(articles, page, limit);
    }

    private List<ArticleVO> paging(List<Article> articles, Integer page, Integer limit) {
        Collections.sort(articles);
        int startIndex = Math.min((page - 1) * limit, articles.size());
        int endIndex = Math.min(page * limit, articles.size());

        articles = articles.subList(startIndex, endIndex);
        List<ArticleVO> articleVOS = new ArrayList<>();
        for (Article article : articles) {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article, articleVO);
            articleVOS.add(articleVO);
        }
        return articleVOS;
    }

    @Override
    public ArticleVO findArticleByTitle(String title) {
        Article article = defaultArticleDao.findArticleByTitle(title);
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article, articleVO);
        return articleVO;
    }

    @Override
    public boolean create(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        return defaultArticleDao.saveArticle(article);
    }

    @Override
    public boolean updateByTitle(ArticleForm articleForm, String title) {
        Article article = new Article();
        BeanUtils.copyProperties(articleForm, article);
        return defaultArticleDao.updateArticleByTitle(article, title);
    }

    @Override
    public boolean deleteByTitle(String title) {
        return defaultArticleDao.deleteArticleByTitle(title);
    }

}
