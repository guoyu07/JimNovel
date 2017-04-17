package com.jim.novel.dao;

import com.jim.novel.constant.ArticleConstant;
import com.jim.novel.constant.enums.ArticleStatus;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.model.Article;
import com.jim.novel.model.ArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
    int countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExample(ArticleExample example);

    /**
     * 文章信息包括作者姓名
     * @param articleId
     * @return
     */
    ArticleVo selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 得到目录的所有文件的数量
     *
     * @param foderId
     * @return Integer
     */
    public int getArticleCountOfDisplayByPath(@Param("path") String path);

    /**
     * 得到目录的文件的列表
     *
     * @param foderId
     * @return List<FileVo>
     */
    List<ArticleVo> getArticleListOfDisplayByPath(
            @Param("path") String path, @Param("offset") int offset,
            @Param("rows") int rows);
    /**
     * @param firstFolderId
     * @param secondFolderId
     * @param thirdFolderId
     * @param fourthFolderId
     * @return
     */
    int getArticleCountByAdminIdAndPath(@Param("adminId") int adminId,
                                               @Param("path") String path)
                                            ;
    /**
     * 得到某种显示的文件的列表
     *
     * @param foderId
     * @return List<FileVo>
     */
    List<ArticleVo> getArticleListByOwnerIdAndPath(
            @Param("ownerId") int ownerId, @Param("path") String path,
            @Param("status") int status);


    /**
     * 得到某种显示的文件的列表
     *
     * @param foderId
     * @return List<FileVo>
     */
    List<ArticleVo> getArticleListByOwnerId(
            @Param("ownerId") int ownerId);


    List<ArticleVo> getPopularList(@Param("offset") int offset, @Param("rows") int rows);

    List<ArticleVo> getAllArticle();

    /**
     * 添加一篇文章
     *
     * @return Integer
     */
    public int addArticle(Article article);

    public int updateStatus(@Param("status")Integer status, @Param("articleId")Integer articleId);
}