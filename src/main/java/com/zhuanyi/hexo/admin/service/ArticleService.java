package com.zhuanyi.hexo.admin.service;

import com.zhuanyi.hexo.admin.obj.dto.ArticleDTO;
import com.zhuanyi.hexo.admin.obj.vo.ArticleListVO;
import com.zhuanyi.hexo.admin.obj.vo.ArticleVO;


public interface ArticleService {

    ArticleListVO findAllArticles(Integer page, Integer limit);

    ArticleListVO findAllTmpArticles(Integer page, Integer limit);

    ArticleVO findArticleById(Long id);

    boolean create(ArticleDTO articleDTO);

    boolean update(ArticleDTO articleDTO);

    boolean deleteById(Long id);

    boolean tmpSave(ArticleDTO articleDTO);

}
