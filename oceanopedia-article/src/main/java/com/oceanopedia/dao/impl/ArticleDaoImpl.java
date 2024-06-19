package com.oceanopedia.dao.impl;

import com.oceanopedia.dao.ArticleDao;
import com.oceanopedia.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Article findArticleById(String id) {
        return mongoTemplate.findById(id, Article.class);
    }

    @Override
    public List<Article> findArticlesByCategory(String category, int page, int rows) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        query.skip((long) page * rows);
        query.limit(rows);
        return mongoTemplate.find(query, Article.class);
    }

    @Override
    public void deleteArticleByID(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Article.class);
    }

    @Override
    public int countArticleByCategory(String category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        return (int) mongoTemplate.count(query, Article.class);
    }

    @Override
    public void saveArticle(Article article) {
        mongoTemplate.save(article);
    }

    @Override
    public void updateArticleStatusById(String id, boolean status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("status", status);
        mongoTemplate.findAndModify(query, update, Article.class);
    }

    @Override
    public void updateArticleInfoById(String id, Article article) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("title", article.getTitle());
        update.set("content", article.getContent());
        update.set("author", article.getAuthor());
        update.set("summary", article.getSummary());
        update.set("views", article.getViews());
        update.set("likes", article.getLikes());
        update.set("status", article.getStatus());
        update.set("publicationDate", article.getPublicationDate());
        update.set("updatedDate", article.getUpdatedDate());
        update.set("category", article.getCategory());
        update.set("coverImage", article.getCoverImage());
        update.set("tags", article.getTags());

        mongoTemplate.findAndModify(query, update, Article.class);
    }

    @Override
    public void updateArticleCoverImageById(String id, String coverImage) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("coverImage", coverImage);
        mongoTemplate.findAndModify(query, update, Article.class);
    }
}