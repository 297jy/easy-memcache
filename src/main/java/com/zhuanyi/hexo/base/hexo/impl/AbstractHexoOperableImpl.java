package com.zhuanyi.hexo.base.hexo.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhuanyi.hexo.admin.obj.pojo.Article;
import com.zhuanyi.hexo.base.hexo.HexoOperable;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public abstract class AbstractHexoOperableImpl implements HexoOperable {

    @Override
    public Article readArticleFromHexoFile(String path) {
        Article article = new Article();
        BufferedReader hexoReader = null;
        try {
            hexoReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

        } catch (Throwable e) {
            log.error("readArticleFromHexoFile失败，{%s}", e);
            return null;
        } finally {
            if (hexoReader != null) {
                try {
                    hexoReader.close();
                } catch (IOException e) {
                    log.error("关闭hexoReader失败，{%s}", e);
                }
            }
        }
        return article;
    }

    @Override
    public boolean writeArticleToHexoFile(String path, Article article) {
        return false;
    }

    public abstract String getArticleTitle(String hexoFileContent);

    public abstract List<String> getArticleTags(String hexoFileContent);

    public abstract List<String> getArticleCategories(String hexoFileContent);

    public abstract String getArticleDate(String hexoFileContent);

    public abstract String getArticleKeywords(String hexoFileContent);

    public abstract String getArticleCover(String cover);

    public abstract String getHexoTitleString(String title);

    public abstract String getHexoTagsString(List<String> tags);

    public abstract String getHexoCategoriesString(List<String> categories);

    public abstract String getHexoDateString(Date date);

    public abstract String getHexoCoverString(String cover);

    public abstract String getHexoKeyWordsString(String cover);

}
