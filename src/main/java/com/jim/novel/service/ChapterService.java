package com.jim.novel.service;

import com.jim.novel.dao.ArticleMapper;
import com.jim.novel.dao.ChapterMapper;
import com.jim.novel.dao.CommentMapper;
import com.jim.novel.dao.UserMapper;
import com.jim.novel.entity.ChapterVo;
import com.jim.novel.model.Chapter;
import com.jim.novel.model.Comment;
import com.jim.novel.model.CommentExample;
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

    @Autowired
    private CommentMapper commentDao;


    public List<Chapter> getChapterList(Integer articleId){

        List<Chapter> list = chapterDao.selectChapterByArticleId(articleId);
        return list;

    }

    public ChapterVo getChapterDetail(Integer chapterId){
        ChapterVo chapterVo = chapterDao.getChapterById(chapterId);
        List<Comment> comments = commentDao.selectCommentsByChapterId(chapterId);
        chapterVo.setComment(comments);
        return chapterVo;

    }
}
