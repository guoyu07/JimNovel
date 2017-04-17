package com.jim.novel.dao;

import com.jim.novel.entity.UserFolderVo;
import com.jim.novel.model.UserFolder;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFolderMapper {
    public int addUserFolder(UserFolder adminFolder);

    public int deleteUserFolder(@Param("userId") int userId,
                                 @Param("folderId") int folderId);

    public List<UserFolderVo> getUserFolderListById(
            @Param("userId") int userId);

    public UserFolderVo getUserFolderById(@Param("userId") int userId,
                                           @Param("folderId") int folderId);

}