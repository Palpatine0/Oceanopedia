package com.oceanopedia.dao;


import com.oceanopedia.entity.Banner;
import com.oceanopedia.entity.Image;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface MultimediaDao {
    List<Banner> findBanners(Query query);

    void saveBanner(Banner banner);

    void updateBanners(String banner1, String banner2, String banner3);

    void saveImage(Image image);
}
