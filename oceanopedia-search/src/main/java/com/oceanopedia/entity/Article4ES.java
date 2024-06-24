package com.oceanopedia.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Document(indexName = "oceanopedia-article")
public class Article4ES {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private String author;

    @Field(type = FieldType.Date)
    private Date publicationDate;

    @Field(type = FieldType.Text)
    private String summary;

    @Field(type = FieldType.Integer)
    private int views;

    @Field(type = FieldType.Keyword)
    private String coverImage;

    @Field(type = FieldType.Integer)
    private int likes;

    @Field(type = FieldType.Boolean)
    private boolean status;

    @Field(type = FieldType.Date)
    private Date updatedDate;

    @Field(type = FieldType.Keyword)
    private List<String> tags;

    @Field(type = FieldType.Keyword)
    private String category;

    public String getLink() {
        return "/detail/" + id;
    }

    public void setLink(String link) {
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
