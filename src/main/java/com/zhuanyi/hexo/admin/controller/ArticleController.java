package com.zhuanyi.hexo.admin.controller;

import com.zhuanyi.hexo.admin.obj.dto.ArticleDTO;
import com.zhuanyi.hexo.admin.obj.form.ArticleForm;
import com.zhuanyi.hexo.admin.obj.vo.ArticleVO;
import com.zhuanyi.hexo.admin.service.ArticleService;
import com.zhuanyi.hexo.base.entity.Result;
import com.zhuanyi.hexo.base.utils.ResultUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService defaultArticleService;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleForm articleForm) {
        boolean result = defaultArticleService.create(new ArticleDTO(articleForm));
        return result ? ResultUtils.success() : ResultUtils.error();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam String title) {
        ArticleVO articleVO = defaultArticleService.findArticleByTitle(title);
        return articleVO != null ? ResultUtils.success(articleVO) : ResultUtils.error();
    }

    @GetMapping("/list")
    public Result list(@RequestParam Integer page, @RequestParam Integer limit) {
        List<ArticleVO> articleVOList = defaultArticleService.findAllArticles(page, limit);
        return CollectionUtils.isEmpty(articleVOList) ? ResultUtils.error() : ResultUtils.success(articleVOList);
    }
}
