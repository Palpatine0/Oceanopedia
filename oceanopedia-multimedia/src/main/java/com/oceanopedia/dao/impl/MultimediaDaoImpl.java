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
    public void updateBanners(String banner1, String banner2, String banner3) {
        List<Banner> existingBanners = mongoTemplate.findAll(Banner.class);
        for (Banner banner : existingBanners) {
            mongoTemplate.remove(banner);
        }
        Banner newBanner1 = new Banner();
        newBanner1.setUrl(banner1);
        mongoTemplate.save(newBanner1);
        Banner newBanner2 = new Banner();
        newBanner2.setUrl(banner2);
        mongoTemplate.save(newBanner2);
        Banner newBanner3 = new Banner();
        newBanner3.setUrl(banner3);
        mongoTemplate.save(newBanner3);
    }

    @Override
    public void saveImage(Image image) {
        mongoTemplate.save(image);
    }

    @Override
    public void saveBanner(Banner banner) {
        mongoTemplate.save(banner);
    }
}
