package com.oceanopedia.service.impl;

import com.oceanopedia.dao.ArticleDao;
import com.oceanopedia.entity.Article;
import com.oceanopedia.service.ArticleService;
import com.oceanopedia.vo.OceanopediaResult;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    private static final String BACKEND_B = "backendB";

    // the IP address info that needs to append in blank image routes
    @Value("${oceanopedia.fdfsBasePath.nginx.prefix}")
    private String nginxPrefix;

    @Override
    @CircuitBreaker(name = BACKEND_B)
    @RateLimiter(name = BACKEND_B)
    @Cacheable(cacheNames = "com:oceanopedia", key = "'getDetails('+#id+')'")
    public Article getArticleByID(String id) {
        Article Articles = articleDao.findArticleById(id);
        return Articles;
    }

    @Override
    public OceanopediaResult deleteArticleByID(String id) {
        try {
            articleDao.deleteArticleByID(id);
            OceanopediaResult ok = new OceanopediaResult();
            ok.setMsg("Article deleted successfully.");
            ok.setStatus(200);
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            OceanopediaResult error = new OceanopediaResult();
            error.setMsg("Failed to delete Article.");
            error.setStatus(500);
            return error;
        }
    }

    @Override
    public OceanopediaResult getArticlesByCategory(String category, int page, int rows) {
        List<Article> ArticleList = articleDao.findArticlesByCategory(category, page, rows);
        OceanopediaResult ok = new OceanopediaResult();
        ok.setCnt(articleDao.countArticleByCategory(category));
        ok.setData(ArticleList);
        return ok;
    }


    @Override
    public OceanopediaResult addArticle(Article Article) {
        try {
            articleDao.saveArticle(Article);
            return OceanopediaResult.ok("Article added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return OceanopediaResult.error("Failed to add Article.");
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:oceanopedia", key = "'getDetails('+#id+')'")
    public OceanopediaResult updateArticleStatusById(String id, String status) {
        try {
            articleDao.updateArticleStatusById(id, status);
            return OceanopediaResult.ok("Status update successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return OceanopediaResult.error("Status update failed");
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:oceanopedia", key = "'getDetails('+#id+')'")
    public OceanopediaResult updateArticleInfoById(String id, Article Article) {
        try {
            articleDao.updateArticleInfoById(id, Article);
            return OceanopediaResult.ok("Info update successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return OceanopediaResult.error("Info update failed");
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:oceanopedia", key = "'getDetails('+#id+')'")
    public OceanopediaResult updateArticleCoverImageById(String id, String coverImage){
        try {
            OceanopediaResult ok = OceanopediaResult.ok();
            articleDao.updateArticleCoverImageById(id, coverImage);
            ok.setMsg("Showcases update successfully.");
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            OceanopediaResult result = OceanopediaResult.error();
            result.setMsg("Showcases update successfully.");
            return OceanopediaResult.error("Showcases update failed");
        }
    }

}
