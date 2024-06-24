package com.oceanopedia.service.impl;

import com.oceanopedia.dao.SearchDao;
import com.oceanopedia.entity.Article;
import com.oceanopedia.entity.Article4ES;
import com.oceanopedia.service.SearchService;
import com.oceanopedia.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SearchDao searchDao;

    @Value("${oceanopedia.fdfsBasePath.nginx.prefix}")
    private String nginxPrefix;

    @Override
    public BaseResult searchByKeyWord(String content) {
        List<Article4ES> article4ESList = searchDao.searchByKeyWord(content);
        return BaseResult.ok(article4ESList);
    }

    @Override
    public void esinit() {
        // Clear the existing index
        searchDao.clearIndex();

        // Load new data from MongoDB and insert into Elasticsearch
        List<Article> articleList = mongoTemplate.findAll(Article.class);
        ArrayList<Article4ES> arrayList = new ArrayList<>();
        for (Article article : articleList) {
            Article4ES article4ES = new Article4ES();
            article4ES.setId(article.getId());
            article4ES.setTitle(article.getTitle());
            article4ES.setContent(article.getContent());
            article4ES.setAuthor(article.getAuthor());
            article4ES.setPublicationDate(article.getPublicationDate());
            article4ES.setSummary(article.getSummary());
            article4ES.setViews(article.getViews());
            article4ES.setCoverImage(nginxPrefix + article.getCoverImage());
            article4ES.setLikes(article.getLikes());
            article4ES.setStatus(article.getStatus());
            article4ES.setUpdatedDate(article.getUpdatedDate());
            article4ES.setTags(article.getTags());
            article4ES.setCategory(article.getCategory());
            arrayList.add(article4ES);
        }
        searchDao.batchInsertToES(arrayList);
    }
}
