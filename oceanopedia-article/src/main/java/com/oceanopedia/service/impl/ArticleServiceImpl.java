package com.oceanopedia.service.impl;

import com.oceanopedia.dao.ArticleDao;
import com.oceanopedia.entity.Article;
import com.oceanopedia.service.ArticleService;
import com.oceanopedia.vo.BaseResult;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    public BaseResult deleteArticleByID(String id) {
        try {
            articleDao.deleteArticleByID(id);
            BaseResult ok = new BaseResult();
            ok.setMsg("Article deleted successfully.");
            ok.setStatus(200);
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult error = new BaseResult();
            error.setMsg("Failed to delete Article.");
            error.setStatus(500);
            return error;
        }
    }

    @Override
    public BaseResult getArticlesByCategory(String category, int page, int rows) {
        List<Article> ArticleList = articleDao.findArticlesByCategory(category, page, rows);
        BaseResult ok = new BaseResult();
        ok.setCnt(articleDao.countArticleByCategory(category));
        ok.setData(ArticleList);
        return ok;
    }


    @Override
    public BaseResult addArticle(Article Article) {
        try {
            articleDao.saveArticle(Article);
            BaseResult ok = BaseResult.ok();
            ok.setMsg("Article added successfully.");
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult error = BaseResult.error();
            error.setMsg("Failed to add Article.");
            return error;
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:oceanopedia", key = "'getDetails('+#id+')'")
    public BaseResult updateArticleStatusById(String id, boolean status) {
        try {
            articleDao.updateArticleStatusById(id, status);
            BaseResult ok = BaseResult.ok();
            ok.setMsg("Status update successfully.");
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult error = BaseResult.error();
            error.setMsg("Status update failed.");
            return error;
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:oceanopedia", key = "'getDetails('+#id+')'")
    public BaseResult updateArticleInfoById(String id, Article Article) {
        try {
            articleDao.updateArticleInfoById(id, Article);
            BaseResult ok = BaseResult.ok();
            ok.setMsg("Info update successfully.");
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult error = BaseResult.error();
            error.setMsg("Info update failed");
            return error;
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:oceanopedia", key = "'getDetails('+#id+')'")
    public BaseResult updateArticleCoverImageById(String id, String coverImage){
        try {
            BaseResult ok = BaseResult.ok();
            articleDao.updateArticleCoverImageById(id, coverImage);
            ok.setMsg("Showcases update successfully.");
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult error = BaseResult.error();
            error.setMsg("Showcases update successfully.");
            return BaseResult.error("Showcases update failed");
        }
    }

    @Override
    public BaseResult getArticleByLikesRank(int rank) {
        BaseResult ok = new BaseResult();
        ok.setData(articleDao.findArticleByLikesRank(rank));
        return ok;
    }

    @Override
    public BaseResult getViewsFromRecentArticles(int maxArticles) {
        List<Integer> views = articleDao.findViewsFromRecentArticles(maxArticles);
        Random random = new Random();

        if (!views.isEmpty()) {
            int minView = Collections.min(views);
            int maxView = Collections.max(views);

            while (views.size() < maxArticles) {
                int randomViews = random.nextInt((maxView - minView) + 1) + minView;
                views.add(randomViews);
            }
        } else {
            // In case there are no views at all, we add zero values.
            while (views.size() < maxArticles) {
                views.add(0);
            }
        }
        BaseResult baseResult = new BaseResult();
        baseResult.setData(views);
        return baseResult;
    }


}
