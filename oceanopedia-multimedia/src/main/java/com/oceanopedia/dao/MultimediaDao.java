package com.oceanopedia.dao;


import com.oceanopedia.entity.Banner;
import com.oceanopedia.entity.Image;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface MultimediaDao {
    List<Banner> findBanners(Query query);

    void saveImage(Image image);

}
