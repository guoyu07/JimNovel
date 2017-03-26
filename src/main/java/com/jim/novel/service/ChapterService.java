package com.jim.novel.service;

import com.jim.novel.dao.ArticleMapper;
import com.jim.novel.dao.ChapterMapper;
import com.jim.novel.dao.UserMapper;
import com.jim.novel.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**章节服务类
 * Created by run on 17/3/25.
 */
@Service
public class ChapterService {

    @Autowired
    private ChapterMapper chapterDao;

    @Autowired
    private ArticleMapper articleDao;

    @Autowired
    private UserMapper userDao;


    public List<Chapter> getChapterList(Integer articleId){

        List<Chapter> list = chapterDao.selectChapterByArticleId(articleId);
        return list;

    }
}
