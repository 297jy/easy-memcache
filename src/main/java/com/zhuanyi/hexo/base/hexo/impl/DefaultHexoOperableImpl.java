package com.zhuanyi.hexo.base.hexo.impl;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("defaultHexoOperable")
public class DefaultHexoOperableImpl extends AbstractHexoOperableImpl {

    @Override
    public String getArticleTitle(String hexoFileContent) {
        return null;
    }

    @Override
    public List<String> getArticleTags(String hexoFileContent) {
        return null;
    }

    @Override
    public List<String> getArticleCategories(String hexoFileContent) {
        return null;
    }

    @Override
    public String getArticleDate(String hexoFileContent) {
        return null;
    }

    @Override
    public String getArticleKeywords(String hexoFileContent) {
        return null;
    }

    @Override
    public String getArticleCover(String cover) {
        return null;
    }

    @Override
    public String getHexoTitleString(String title) {
        return null;
    }

    @Override
    public String getHexoTagsString(List<String> tags) {
        return null;
    }

    @Override
    public String getHexoCategoriesString(List<String> categories) {
        return null;
    }

    @Override
    public String getHexoDateString(Date date) {
        return null;
    }

    @Override
    public String getHexoCoverString(String cover) {
        return null;
    }

    @Override
    public String getHexoKeyWordsString(String cover) {
        return null;
    }
}
