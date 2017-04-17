package com.jim.novel.service;

import com.jim.novel.constant.ArticleConstant;
import com.jim.novel.constant.enums.ArticleStatus;
import com.jim.novel.dao.ArticleMapper;
import com.jim.novel.dao.ChapterMapper;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.entity.PageVo;
import com.jim.novel.exception.ArticleNotFoundException;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Article;
import com.jim.novel.model.Chapter;
import com.jim.novel.model.Folder;
import com.jim.novel.model.User;
import com.jim.novel.utils.MediaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by run on 17/3/14.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleDao;

    @Autowired
    private FolderService folderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChapterMapper chapterDao;

    @Cacheable(value = "article", key = "'getArticleById_'+#articleId")
    public ArticleVo getArticleById(int articleId)
            throws ArticleNotFoundException, FolderNotFoundException {
        ArticleVo articleVo = articleDao.selectByPrimaryKey(articleId);
        FolderVo articleFolder = folderService.getFolderById(articleVo.getFolderId());
        String authorNmae = userService.getAuthorNmaeByUserId(articleVo.getOwnerId());
        articleVo.setAuthor(authorNmae);
        articleVo.setFolder(articleFolder);
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

    public List<ArticleVo> getArticleList(){
        List<ArticleVo> articleVoList = articleDao.getAllArticle();
        for (ArticleVo article :
                articleVoList) {
            FolderVo articleFolder = null;
            try {
                articleFolder = folderService.getFolderById(article.getFolderId());
            } catch (FolderNotFoundException e) {
                e.printStackTrace();
            }
            User user = userService.getUserInfo(article.getOwnerId());
            article.setUser(user);
            article.setFolder(articleFolder);
        }
        return articleVoList;
    }

    /**
     * @param adminId
     * @param folderId
     * @return
     * @throws FolderNotFoundException
     */
    public int getArticleCountByAdminIdAndFolderId(int adminId,
                                                   int folderId, ArticleStatus status)
            throws FolderNotFoundException {
        String path = "";
        if (folderId != 0) {
            Folder folder = folderService.getFolderById(folderId);
            path = folder.getPath();
        }
        return articleDao.getArticleCountByAdminIdAndPath(adminId,
                path);
    }

    /**获取热门文章
     *
     * @param pageNum
     * @param rows
     * @return
     */
    public PageVo<ArticleVo> getRandomArticle(int pageNum,int rows,int count) throws FolderNotFoundException {
        PageVo<ArticleVo> pageVo = new PageVo<>(pageNum);
        pageVo.setRows(rows);
        pageVo.setCount(count);
        List<ArticleVo> articleVoList = articleDao.getPopularList(pageVo.getOffset(),rows);
        for (ArticleVo article :
                articleVoList) {
            FolderVo articleFolder = folderService.getFolderById(article.getFolderId());
            String authorNmae = userService.getAuthorNmaeByUserId(article.getOwnerId());
            article.setAuthor(authorNmae);
            article.setFolder(articleFolder);
        }
        pageVo.setList(articleVoList);
        return pageVo;

    }

    /**获取热门文章
     *
     * @param pageNum
     * @param rows
     * @return
     */
    public PageVo<ArticleVo> getPopularArticle(int pageNum,int rows,int count) throws FolderNotFoundException {
        PageVo<ArticleVo> pageVo = new PageVo<>(pageNum);
        pageVo.setRows(rows);
        pageVo.setCount(count);
        List<ArticleVo> articleVoList = articleDao.getPopularList(pageVo.getOffset(),rows);
        for (ArticleVo article :
                articleVoList) {
            FolderVo articleFolder = folderService.getFolderById(article.getFolderId());
            String authorNmae = userService.getAuthorNmaeByUserId(article.getOwnerId());
            article.setAuthor(authorNmae);
            article.setFolder(articleFolder);
        }
        pageVo.setList(articleVoList);
        return pageVo;

    }
    /**
     * 得到目录的显示的文件分页
     *
     * @param folderId
     * @return pageVo
     * @throws FolderNotFoundException
     */
    @Cacheable(value = "article")
    public PageVo<ArticleVo> getArticlePageByFolderId(int folderId,
                                                      int pageNum, int rows) throws FolderNotFoundException {
        PageVo<ArticleVo> pageVo = new PageVo<ArticleVo>(pageNum);
        FolderVo folder = folderService.getFolderById(folderId);
        pageVo.setRows(rows);
        pageVo.setCount(articleDao
                .getArticleCountOfDisplayByPath(folder
                        .getPath()));
        List<ArticleVo> articlelist = articleDao
                .getArticleListOfDisplayByPath(
                        folder.getPath(),
                        pageVo.getOffset(),
                        pageVo.getRows());
        for (ArticleVo artcle : articlelist) {
            FolderVo artcleFolder = folderService
                    .getFolderById(artcle.getFolderId());
            String authorNmae = userService.getAuthorNmaeByUserId(artcle.getOwnerId());
            artcle.setAuthor(authorNmae);
            artcle.setFolder(artcleFolder);
        }
        pageVo.setList(articlelist);
        return pageVo;
    }

    /**
     * 查询用户指定分类的目录下的小说列表
     * @param ownerId
     * @param folderId
     * @param status
     * @return
     * @throws FolderNotFoundException
     */
    public List<ArticleVo> getArticleListByOwnerIdAndFolderId(int ownerId,
                                                              int folderId, ArticleStatus status) throws FolderNotFoundException {
        String path = "";
        if (folderId != 0) {
            Folder folder = folderService.getFolderById(folderId);
            path = folder.getPath();
        }
        List<ArticleVo> articleList = articleDao
                .getArticleListByOwnerIdAndPath(ownerId, path,
                        status.getValue());
        return articleList;
    }

    /**
     * 查询某个用户所有小说的列表
     * @param ownerId
     * @return
     * @throws FolderNotFoundException
     */
    public List<ArticleVo> getArticleListByOwnerId(int ownerId) throws FolderNotFoundException {
        List<ArticleVo> articleList = articleDao.getArticleListByOwnerId(ownerId);
        for (ArticleVo article :
                articleList) {
            FolderVo articleFolder = folderService.getFolderById(article.getFolderId());
            String authorNmae = userService.getAuthorNmaeByUserId(article.getOwnerId());
            article.setAuthor(authorNmae);
            article.setFolder(articleFolder);
        }
        return articleList;
    }

    public ArticleVo getArticleWithAutorName(int articleId){

        return  articleDao.selectByPrimaryKey(articleId);

    }


    /**
     * @param folderId
     * @param adminId
     * @param title
     * @param summary
     * @param status
     * @param content
     * @param file
     * @param createTime
     * @return
     * @throws FolderNotFoundException
     * @throws UploadException
     * @throws IOException
     */
    @CacheEvict(value = "article", allEntries = true)
    public Article addArticle(int folderId, int ownerId, String title, String keyword,MultipartFile fileBig,MultipartFile fileSmall) throws FolderNotFoundException, IOException {
        FolderVo folder = folderService.getFolderById(folderId);
        folder.setWidth(0);
        folder.setHeight(0);
        Article article = new Article();
        Date now = new Date();
        String imgBig = "",imgSmall = "";
        if (fileBig != null && !fileBig.isEmpty()) {
            imgBig = MediaUtils.saveImage(fileBig, folder.getWidth(),
                    folder.getHeight());
        }
        if (fileSmall != null && !fileSmall.isEmpty()) {
            imgSmall = MediaUtils.saveImage(fileSmall, folder.getWidth(),
                    folder.getHeight());
        }
        article.setImgUrl(imgBig);
        article.setSmallImgUrl(imgSmall);
        article.setFolderId(folder.getFolderId());
        article.setPath(folder.getPath());
        article.setOwnerId(ownerId);
        article.setTitle(title);
        article.setViewCount(0);
        article.setStatus(ArticleStatus.ORIGIN.getValue());
        article.setCreateTime(now);
        article.setModifyTime(now);
        article.setKeyword(keyword);
        articleDao.addArticle(article);
        return articleDao.selectByPrimaryKey(article.getArticleId());
    }

    public void deleteArticle(Integer articleId){
        int c = articleDao.deleteByPrimaryKey(articleId);
        if(c!=0){
            List<Chapter> list = chapterDao.selectChapterByArticleId(articleId);
            for (Chapter tmp:list
                 ) {
                chapterDao.deleteById(tmp.getArticleId());
            }

        }
    }

    /**
     * 审核更新状态
     * @param status
     * @param articleId
     * @return
     */
    public int updateStatus(Integer status,Integer articleId){
        return articleDao.updateStatus(status,articleId);
    }


}

