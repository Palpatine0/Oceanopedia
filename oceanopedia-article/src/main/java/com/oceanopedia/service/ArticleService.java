package com.oceanopedia.service;


import com.oceanopedia.entity.Article;
import com.oceanopedia.vo.BaseResult;

public interface ArticleService {
    Article getArticleByID(String id);

    BaseResult deleteArticleByID(String id);

    BaseResult getArticlesByCategory(String city, int page, int rows);

    BaseResult addArticle(Article article);

    BaseResult updateArticleStatusById(String id, boolean status);

    BaseResult updateArticleInfoById(String id, Article article);

    BaseResult updateArticleCoverImageById(String id, String coverImageUrl);

    BaseResult getArticleByLikesRank(int rank);

}
