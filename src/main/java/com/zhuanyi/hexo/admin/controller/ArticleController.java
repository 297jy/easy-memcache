package com.zhuanyi.hexo.admin.controller;

import com.zhuanyi.hexo.admin.obj.form.ArticleForm;
import com.zhuanyi.hexo.admin.service.ArticleService;
import com.zhuanyi.hexo.base.entity.Result;
import com.zhuanyi.hexo.base.utils.ResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService defaultArticleService;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleForm articleForm) {
        boolean result = defaultArticleService.create(articleForm);
        return result ? ResultUtils.success() : ResultUtils.error();
    }

}
