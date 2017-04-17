package com.jim.novel.dao;

import com.jim.novel.constant.enums.FolderDisplay;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.model.Folder;
import com.jim.novel.model.FolderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FolderMapper {
    int countByExample(FolderExample example);

    int deleteByExample(FolderExample example);

    boolean deleteByPrimaryKey(Integer folderId);

    int insert(Folder record);

    int insertSelective(Folder record);

    List<Folder> selectByExampleWithBLOBs(FolderExample example);

    List<Folder> selectByExample(FolderExample example);

    FolderVo selectByPrimaryKey(int folderId);

    int updateByExampleSelective(@Param("record") Folder record, @Param("example") FolderExample example);

    int updateByExampleWithBLOBs(@Param("record") Folder record, @Param("example") FolderExample example);

    int updateByExample(@Param("record") Folder record, @Param("example") FolderExample example);

    int updateByPrimaryKeySelective(Folder record);

    int updateByPrimaryKeyWithBLOBs(Folder record);

    int updateByPrimaryKey(Folder record);

    public FolderVo getFolderById(@Param("folderId") int folderId);

    /**
     * 通过ename获得指定目录
     *
     * @param ename
     *
     * @return
     */
    public Folder getFolderByEname(@Param("ename") String ename);

    /**
     * 得到所有子目录
     *
     * @param fatherId
     * @return List<FolderVo>
     */
    public List<FolderVo> getFolderListByFatherId(
            @Param("fatherId") int fatherId,
            @Param("display") int display);

    /**
     * 得到所有子目录
     *
     * @param fatherId
     * @return List<FolderVo>
     */
    public List<FolderVo> getFolderListBySort();

    public List<FolderVo> getAllFolderList();

    /**
     * 增加目录
     *
     * @return Integer
     */
    public int addFolder(Folder folder);

    public int updatePath(@Param("folderId") long folderId,
                          @Param("path") String path);

}