package com.zhuanyi.hexo.base.hexo;

import com.zhuanyi.hexo.admin.obj.pojo.Article;

public interface HexoOperable {

    Article readArticleFromHexoFile(String path);

    boolean writeArticleToHexoFile(String path, Article article);
}
