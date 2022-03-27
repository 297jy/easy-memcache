package com.zhuanyi.hexo.base.hexo.impl;

import com.zhuanyi.hexo.admin.config.SystemConfig;
import com.zhuanyi.hexo.admin.obj.pojo.Article;
import com.zhuanyi.hexo.base.hexo.HexoFileManage;
import com.zhuanyi.hexo.base.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public abstract class AbstractHexoFileManageImpl implements HexoFileManage {

    @Resource
    private SystemConfig systemConfig;

    @Override
    public Article readArticle(String title) {
        String path = systemConfig.getHexoSourcePath() + File.separator + title + ".md";
        if (!FileUtils.existFile(title)) {
            return null;
        }

        Article article = new Article();
        List<String> lines = FileUtils.readAllLinesFromFile(path);
        if (CollectionUtils.isEmpty(lines)) {
            return null;
        }

        article.setTitle(getArticleTitle(lines));
        article.setCategories(getArticleCategories(lines));
        article.setContent(getArticleContent(lines));
        article.setCover(getArticleCover(lines));
        article.setKeyWords(getArticleKeywords(lines));
        article.setTags(getArticleTags(lines));
        article.setPublishTime(getArticleDate(lines));
        return article;
    }

    @Override
    public boolean saveArticle(Article article) {
        String path = systemConfig.getHexoSourcePath() + File.separator + article.getTitle() + ".md";
        List<String> hexoFileLines = new ArrayList<>();
        hexoFileLines.add("---");
        hexoFileLines.addAll(getHexoTitle(article.getTitle()));
        hexoFileLines.addAll(getHexoTags(article.getTags()));
        hexoFileLines.addAll(getHexoCategories(article.getCategories()));
        hexoFileLines.addAll(getHexoCover(article.getCover()));
        hexoFileLines.addAll(getHexoKeyWords(article.getKeyWords()));
        hexoFileLines.addAll(getHexoDate(article.getPublishTime()));
        hexoFileLines.add("---");
        hexoFileLines.addAll(getHexoContent(article.getContent()));

        String hexoFileContent = StringUtils.join(hexoFileLines, "\r\n");
        return FileUtils.writeContentToFile(path, hexoFileContent);
    }

    @Override
    public boolean deleteArticle(String title) {
        String path = systemConfig.getHexoSourcePath() + File.separator + title + ".md";
        return FileUtils.deleteFile(path);
    }

    public abstract String getArticleTitle(List<String> hexoContentLines);

    public abstract List<String> getArticleTags(List<String> hexoContentLines);

    public abstract List<String> getArticleCategories(List<String> hexoContentLines);

    public abstract String getArticleDate(List<String> hexoContentLines);

    public abstract String getArticleKeywords(List<String> hexoContentLines);

    public abstract String getArticleCover(List<String> hexoContentLines);

    public abstract String getArticleContent(List<String> hexoContentLines);

    public abstract List<String> getHexoTitle(String title);

    public abstract List<String> getHexoTags(List<String> tags);

    public abstract List<String> getHexoCategories(List<String> categories);

    public abstract List<String> getHexoDate(String date);

    public abstract List<String> getHexoCover(String cover);

    public abstract List<String> getHexoKeyWords(String keywords);

    public abstract List<String> getHexoContent(String content);

}
