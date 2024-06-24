package com.oceanopedia.dao;

import com.oceanopedia.entity.Article4ES;

import java.util.List;

public interface SearchDao {
    List<Article4ES> searchByKeyWord(String content);


    void batchInsertToES(List<Article4ES> items);
}

