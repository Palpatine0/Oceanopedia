package com.oceanopedia.controller;

import com.oceanopedia.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.oceanopedia.entity.Article;
import com.oceanopedia.vo.OceanopediaResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/getArticleByID")
    public Article getArticleByID(String id) {
        return articleService.getArticleByID(id);
    }

    @DeleteMapping("/deleteArticleByID")
    public OceanopediaResult deleteArticleByID(String id) {
        return articleService.deleteArticleByID(id);
    }

    @GetMapping("/getArticlesByCategory")
    public OceanopediaResult getArticlesByCategory(String category, int page, @RequestParam(defaultValue = "3") int rows) {
        return articleService.getArticlesByCategory(category, page, rows);
    }

    @PostMapping("/addArticle")
    public OceanopediaResult addArticle(
            String title, String content, String author, String summary, int views, int likes, String status,
            @RequestParam(required = false) String publicationDate, @RequestParam(required = false) String updatedDate,
            String category, String coverImage, @RequestParam(required = false) List<String> tags) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date pubDate;
        Date updDate;

        if (publicationDate == null || publicationDate.isEmpty()) {
            pubDate = new Date();
        } else {
            pubDate = formatter.parse(publicationDate);
        }

        if (updatedDate == null || updatedDate.isEmpty()) {
            updDate = new Date();
        } else {
            updDate = formatter.parse(updatedDate);
        }

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setSummary(summary);
        article.setViews(views);
        article.setLikes(likes);
        article.setStatus(status);
        article.setPublicationDate(pubDate);
        article.setUpdatedDate(updDate);
        article.setCategory(category);
        article.setCoverImage(coverImage);
        article.setTags(tags != null ? tags : new ArrayList<>());

        return articleService.addArticle(article);
    }

    @PostMapping("/updateArticleStatusById")
    public OceanopediaResult updateArticleStatusById(String id, String status) {
        return articleService.updateArticleStatusById(id, status);
    }

    @PostMapping("/updateArticleInfoById")
    public OceanopediaResult updateArticleInfoById(
            String id, String title, String content, String author, String summary, int views, int likes, String status,
            @RequestParam(required = false) String publicationDate, @RequestParam(required = false) String updatedDate,
            String category, String coverImage, @RequestParam(required = false) List<String> tags) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date pubDate;
        Date updDate;

        if (publicationDate == null || publicationDate.isEmpty()) {
            pubDate = new Date();
        } else {
            pubDate = formatter.parse(publicationDate);
        }

        if (updatedDate == null || updatedDate.isEmpty()) {
            updDate = new Date();
        } else {
            updDate = formatter.parse(updatedDate);
        }

        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setSummary(summary);
        article.setViews(views);
        article.setLikes(likes);
        article.setStatus(status);
        article.setPublicationDate(pubDate);
        article.setUpdatedDate(updDate);
        article.setCategory(category);
        article.setCoverImage(coverImage);
        article.setTags(tags != null ? tags : new ArrayList<>());

        return articleService.updateArticleInfoById(id, article);
    }

    @PostMapping("/updateArticleCoverImageById")
    public OceanopediaResult updateArticleCoverImageById(String id, String coverImage) {
        return articleService.updateArticleCoverImageById(id, coverImage);
    }
}
