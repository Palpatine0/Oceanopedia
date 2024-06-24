package com.oceanopedia.dao.impl;


import com.oceanopedia.dao.SearchDao;
import com.oceanopedia.entity.Article4ES;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Value("${oceanopedia.search.init.enabled}")
    private boolean initEnabled = false;


    @Override
    public List<Article4ES> searchByKeyWord(String content) {

        BoolQueryBuilder shouldBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", content));
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(shouldBuilder);

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Article4ES> resultPage = elasticsearchRestTemplate.search(query, Article4ES.class);
        List<SearchHit<Article4ES>> searchHits = resultPage.getSearchHits();
        List<Article4ES> res = new ArrayList<>();
        for (SearchHit<Article4ES> searchHit : searchHits) {
            Article4ES article4ES = searchHit.getContent();
            res.add(article4ES);
        }

        return res;
    }


    @Override
    public void batchInsertToES(List<Article4ES> articles) {
        // Creating an Elasticsearch index if it doesn't already exist.
        if (initEnabled) {
            createIndex();
        }

        // Convert to IndexQuery, so it can be inserted to ES
        List<IndexQuery> indexQueries = new ArrayList<>();
        for (Article4ES article : articles) {
            indexQueries.add(new IndexQueryBuilder().withObject(article).build());
        }

        // Insert
        elasticsearchRestTemplate.bulkIndex(indexQueries, Article4ES.class);
    }

    private void createIndex() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Article4ES.class);
    }


}
