package com.zhuanyi.hexo.base.hexo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component("defaultHexoFileManage")
public class DefaultHexoFileManageImpl extends AbstractHexoFileManageImpl {

    @Override
    public String getArticleAuthor(List<String> hexoContentLines) {
        if (CollectionUtils.isEmpty(hexoContentLines)) {
            return null;
        }

        String titleLine = hexoContentLines.stream().filter(line -> line.trim().startsWith("author:"))
                .findFirst().orElse("");
        return titleLine.replace("author:", "").trim();
    }

    @Override
    public String getArticleTitle(List<String> hexoContentLines) {
        if (CollectionUtils.isEmpty(hexoContentLines)) {
            return null;
        }

        String titleLine = hexoContentLines.stream().filter(line -> line.trim().startsWith("title:"))
                .findFirst().orElse("");
        return titleLine.replace("title:", "").trim();
    }

    @Override
    public List<String> getArticleTags(List<String> hexoContentLines) {
        return findAllLabels(hexoContentLines, "tags:");
    }

    @Override
    public List<String> getArticleCategories(List<String> hexoContentLines) {
        return findAllLabels(hexoContentLines, "categories:");
    }

    @Override
    public String getArticleDate(List<String> hexoContentLines) {
        if (CollectionUtils.isEmpty(hexoContentLines)) {
            return null;
        }
        String dateLine = hexoContentLines.stream().filter(line -> line.trim().startsWith("date:"))
                .findFirst().orElse("");
        return dateLine.replace("date:", "").trim();
    }

    @Override
    public String getArticleKeywords(List<String> hexoContentLines) {
        if (CollectionUtils.isEmpty(hexoContentLines)) {
            return null;
        }
        String keyWordsLine = hexoContentLines.stream().filter(line -> line.trim().startsWith("keywords:"))
                .findFirst().orElse("");
        return keyWordsLine.replace("keywords:", "").trim();
    }

    @Override
    public String getArticleCover(List<String> hexoContentLines) {
        if (CollectionUtils.isEmpty(hexoContentLines)) {
            return null;
        }
        String keyWordsLine = hexoContentLines.stream().filter(line -> line.trim().startsWith("cover:"))
                .findFirst().orElse("");
        return keyWordsLine.replace("cover:", "").trim();
    }

    @Override
    public String getArticleContent(List<String> hexoContentLines) {
        if (CollectionUtils.isEmpty(hexoContentLines)) {
            return null;
        }
        int index = hexoContentLines.lastIndexOf("---");
        if (index != -1) {
            int startIndex = Math.min(index + 1, hexoContentLines.size());
            return StringUtils.join(hexoContentLines.subList(startIndex, hexoContentLines.size()), "").trim();
        }
        return "";
    }

    @Override
    public List<String> getHexoAuthor(String author) {
        return Collections.singletonList("author: " + author);
    }

    @Override
    public List<String> getHexoTitle(String title) {
        return Collections.singletonList("title: " + title);
    }

    @Override
    public List<String> getHexoTags(List<String> tags) {
        List<String> hexoTags = new ArrayList<>();
        hexoTags.add("tags:");
        for (String tag : tags) {
            hexoTags.add("  - " + tag);
        }
        return hexoTags;
    }

    @Override
    public List<String> getHexoCategories(List<String> categories) {
        List<String> hexoCategories = new ArrayList<>();
        hexoCategories.add("categories:");
        for (String category : categories) {
            hexoCategories.add("  - " + category);
        }
        return hexoCategories;
    }

    @Override
    public List<String> getHexoDate(String date) {
        return Collections.singletonList("date: " + date);
    }

    @Override
    public List<String> getHexoCover(String cover) {
        return Collections.singletonList("cover: " + cover);
    }

    @Override
    public List<String> getHexoKeyWords(String keywords) {
        return Collections.singletonList("keywords: " + keywords);
    }

    @Override
    public List<String> getHexoContent(String content) {
        return Collections.singletonList(content);
    }

    private List<String> findAllLabels(List<String> hexoContentLines, String label) {
        if (CollectionUtils.isEmpty(hexoContentLines)) {
            return null;
        }

        List<String> labels = new ArrayList<>();
        for (int i = 0; i < hexoContentLines.size() - 1; i++) {
            if (hexoContentLines.get(i).trim().startsWith(label)) {
                int j = i + 1;
                while (j < hexoContentLines.size() && hexoContentLines.get(j).trim().startsWith("-")) {
                    String tag = hexoContentLines.get(j).trim().replace("-", "").trim();
                    labels.add(tag);
                    j++;
                }
                return labels;
            }
        }
        return labels;
    }
}
