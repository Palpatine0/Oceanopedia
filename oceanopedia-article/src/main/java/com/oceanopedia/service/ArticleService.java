package com.oceanopedia.service;


import com.oceanopedia.entity.Article;
import com.oceanopedia.vo.OceanopediaResult;

public interface ArticleService {
    Article getArticleByID(String id);

    OceanopediaResult deleteArticleByID(String id);

    OceanopediaResult getArticlesByCategory(String city, int page, int rows);

    OceanopediaResult addArticle(Article article);

    OceanopediaResult updateArticleStatusById(String id, boolean status);

    OceanopediaResult updateArticleInfoById(String id, Article article);

    OceanopediaResult updateArticleCoverImageById(String id, String coverImageUrl);

}
