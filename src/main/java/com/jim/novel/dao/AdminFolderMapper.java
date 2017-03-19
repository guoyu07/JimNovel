package com.jim.novel.dao;

import com.jim.novel.model.AdminFolder;
import com.jim.novel.model.AdminFolderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminFolderMapper {
    int countByExample(AdminFolderExample example);

    int deleteByExample(AdminFolderExample example);

    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminFolder record);

    int insertSelective(AdminFolder record);

    List<AdminFolder> selectByExample(AdminFolderExample example);

    AdminFolder selectByPrimaryKey(Integer adminId);

    int updateByExampleSelective(@Param("record") AdminFolder record, @Param("example") AdminFolderExample example);

    int updateByExample(@Param("record") AdminFolder record, @Param("example") AdminFolderExample example);

    int updateByPrimaryKeySelective(AdminFolder record);

    int updateByPrimaryKey(AdminFolder record);
}