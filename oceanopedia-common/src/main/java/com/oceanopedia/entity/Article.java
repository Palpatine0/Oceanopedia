package com.oceanopedia.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Article {

    private String id;

    private String title;

    private String content;

    private String author;

    private Date publicationDate;

    private String summary;

    private int views;

    private String coverImage;

    private int likes;

    private String status;

    private Date updatedDate;

    private List<String> tags;

    private String category;

    public String getLink() {
        return "/article/" + id;
    }

}
