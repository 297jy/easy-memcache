package com.zhuanyi.hexo.admin.service;

import com.zhuanyi.hexo.admin.obj.dto.ArticleDTO;
import com.zhuanyi.hexo.admin.obj.form.ArticleForm;
import com.zhuanyi.hexo.admin.obj.vo.ArticleVO;

import java.util.List;

public interface ArticleService {

    List<ArticleVO> findAllArticles(Integer page, Integer limit);

    ArticleVO findArticleByTitle(String title);

    boolean create(ArticleDTO articleDTO);

    boolean updateByTitle(ArticleForm articleForm, String title);

    boolean deleteByTitle(String title);

}
