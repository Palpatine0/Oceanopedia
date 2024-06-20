package com.oceanopedia.dao;



import com.oceanopedia.entity.Article;

import java.util.List;

public interface ArticleDao {
    Article findArticleById(String id);

    List<Article> findArticlesByCategory(String category, int page, int rows);

    void deleteArticleByID(String id);

    int countArticleByCategory(String category);

    void saveArticle(Article Article);

    void updateArticleStatusById(String id, boolean status);

    void updateArticleInfoById(String id, Article Article);

    void updateArticleCoverImageById(String id, String coverImage);

    Article findArticleByLikesRank(int rank);

}
