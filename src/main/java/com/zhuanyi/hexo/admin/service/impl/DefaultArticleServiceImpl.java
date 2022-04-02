package com.zhuanyi.hexo.admin.service.impl;

import com.zhuanyi.hexo.admin.dao.ArticleDao;
import com.zhuanyi.hexo.admin.obj.dto.ArticleDTO;
import com.zhuanyi.hexo.admin.obj.pojo.Article;
import com.zhuanyi.hexo.admin.obj.vo.ArticleListVO;
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
    public ArticleListVO findAllArticles(Integer page, Integer limit) {
        List<Article> articles = defaultArticleDao.findAllArticles();
        return paging(articles, page, limit);
    }

    private ArticleListVO paging(List<Article> articles, Integer page, Integer limit) {
        Collections.sort(articles);
        int startIndex = Math.min((page - 1) * limit, articles.size());
        int endIndex = Math.min(page * limit, articles.size());
        int no = startIndex + 1;
        int total = articles.size();

        articles = articles.subList(startIndex, endIndex);
        List<ArticleVO> articleVOS = new ArrayList<>();
        for (Article article : articles) {
            ArticleVO articleVO = new ArticleVO(article);
            articleVO.setNo(no++);
            articleVOS.add(articleVO);
        }
        return new ArticleListVO(articleVOS, total);
    }

    @Override
    public ArticleVO findArticleById(Long id) {
        Article article = defaultArticleDao.findArticleById(id);
        return new ArticleVO(article);
    }

    @Override
    public boolean create(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        return defaultArticleDao.saveArticle(article);
    }

    @Override
    public boolean update(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        return defaultArticleDao.updateArticle(article);
    }

    @Override
    public boolean deleteById(Long id) {
        return defaultArticleDao.deleteArticleById(id);
    }

}
