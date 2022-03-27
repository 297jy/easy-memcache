package com.zhuanyi.hexo.base.hexo;

import com.zhuanyi.hexo.admin.obj.pojo.Article;

public interface HexoFileManage {

    Article readArticle(String title);

    boolean saveArticle(Article article);

    boolean deleteArticle(String title);

}
