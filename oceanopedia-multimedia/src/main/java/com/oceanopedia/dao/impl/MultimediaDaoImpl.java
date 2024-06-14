package com.oceanopedia.dao.impl;

import com.oceanopedia.entity.Banner;
import com.oceanopedia.dao.MultimediaDao;
import com.oceanopedia.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MultimediaDaoImpl implements MultimediaDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Banner> findBanners(Query query) {
        List<Banner> banner = mongoTemplate.find(query, Banner.class);
        return banner;
    }

    @Override
    public void saveImage(Image image) {
        mongoTemplate.save(image);
    }


}
