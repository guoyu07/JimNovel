package com.jim.novel.service;

import com.jim.novel.dao.ArticleMapper;
import com.jim.novel.dao.ChapterMapper;
import com.jim.novel.dao.CommentMapper;
import com.jim.novel.dao.UserMapper;
import com.jim.novel.entity.ChapterVo;
import com.jim.novel.model.Chapter;
import com.jim.novel.model.Comment;
import com.jim.novel.model.CommentExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public ChapterVo addChapter(String title, Integer articleId, String content, String createTime, Integer sort) {
        Chapter chapter = new Chapter();
        chapter.setArticleId(articleId);
        chapter.setContent(content);
        chapter.setChapterTitle(title);
        chapter.setCommentCount(0);
        chapter.setSort(sort);
        Date now =  new Date();
        if (StringUtils.isBlank(createTime)) {
            chapter.setCreateTime(now);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "yyyy-MM-dd");
            Date date;
            try {
                date = sdf.parse(createTime);
            } catch (ParseException e) {
                date = now;
            }
            chapter.setCreateTime(date);
            chapter.setModifyTime(date);
        }
        chapterDao.addChapter(chapter);
        return chapterDao.selectByPrimaryKey(chapter.getChapterId());

    }

    public int updateChapter(Integer chapterId,String title, Integer articleId, String content, Integer sort) {
        Chapter chapter = new Chapter();
        chapter.setArticleId(articleId);
        chapter.setContent(content);
        chapter.setChapterTitle(title);
        chapter.setSort(sort);
        chapter.setChapterId(chapterId);
        Date now =  new Date();
        chapter.setModifyTime(now);

        return chapterDao.updateChapter(chapter);

    }

    public int delete(Integer chapterId){
        return chapterDao.deleteById(chapterId);
    }

}
