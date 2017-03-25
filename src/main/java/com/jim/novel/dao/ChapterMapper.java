package com.jim.novel.dao;

import com.jim.novel.model.Chapter;

import java.util.List;

public interface ChapterMapper {

    List<Chapter> selectChapterByArticleId(int articleId);
}