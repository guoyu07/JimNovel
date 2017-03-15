package com.jim.novel.service;

import com.jim.novel.dao.ArticleMapper;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.exception.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by run on 17/3/14.
 */
public class ArticleService {
    @Autowired
    private ArticleMapper articleDao;

    @Autowired
    private FolderService folderService;


    @Cacheable(value = "article", key = "'getArticleById_'+#articleId")
    public ArticleVo getArticleById(int articleId)
            throws ArticleNotFoundException {
        ArticleVo articleVo = articleDao.selectByPrimaryKey(articleId);
        if (articleVo == null) {
            throw new ArticleNotFoundException(articleId
                    + " 文件，不存在");
        } else {
            return articleVo;
        }
    }

    /**
     * @param path
     * @param offset
     * @param rows
     * @return
     */
    public List<ArticleVo> getArticleListOfDisplayByPath(String path,
                                                         int offset, int rows) {
        List<ArticleVo> articlelist = articleDao
                .getArticleListOfDisplayByPath(path, offset,
                        rows);
        return articlelist;
    }




}

