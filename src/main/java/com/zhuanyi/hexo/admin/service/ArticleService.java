package com.zhuanyi.hexo.admin.service;

import com.zhuanyi.hexo.admin.obj.form.ArticleForm;
import com.zhuanyi.hexo.admin.obj.vo.ArticleVO;

import java.util.List;

public interface ArticleService {

    List<ArticleVO> findAllArticles();

    boolean create(ArticleForm articleForm);

    boolean updateByTitle(ArticleForm articleForm, String title);

    boolean deleteByTitle(String title);

}
