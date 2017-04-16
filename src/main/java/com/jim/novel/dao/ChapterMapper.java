package com.jim.novel.dao;

import com.jim.novel.entity.ChapterVo;
import com.jim.novel.model.Chapter;

import java.util.List;

public interface ChapterMapper {

    List<Chapter> selectChapterByArticleId(int articleId);

    ChapterVo getChapterById(int chapterId);

    int addChapter(Chapter chapter);

    ChapterVo selectByPrimaryKey(Integer chapterId);

    int updateChapter(Chapter chapter);

    int deleteByArticleId(Integer articleId);

    int deleteById(Integer chapterId);


}