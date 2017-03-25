package com.jim.novel.entity;

import com.jim.novel.model.Article;
import com.jim.novel.model.Chapter;

/**
 * Created by run on 17/3/25.
 */
public class ChapterVo extends Chapter {
    private Article article;
    private String author;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
