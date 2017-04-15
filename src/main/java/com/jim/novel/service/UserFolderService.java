package com.jim.novel.service;


import com.jim.novel.dao.UserFolderMapper;
import com.jim.novel.entity.UserFolderVo;
import com.jim.novel.model.UserFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserFolderService {

	@Autowired
	private UserFolderMapper userFolderDao;

	@Autowired
	private FolderService folderService;

	@CacheEvict(value = "folder", allEntries = true)
	public UserFolder addUserFolder(int userId, int folderId) {
		UserFolder userFolder = new UserFolder();
		userFolder.setUserId(userId);
		userFolder.setFolderId(folderId);
		userFolder.setCreateTime(new Date());
		userFolderDao.addUserFolder(userFolder);
		return userFolder;
	}

	@CacheEvict(value = "folder", allEntries = true)
	public int deleteUserFolder(int userId, int folderId) {
		return userFolderDao.deleteUserFolder(userId, folderId);
	}

	public List<UserFolderVo> getUserFolderListById(int userId) {
		List<UserFolderVo> list = userFolderDao
				.getUserFolderListById(userId);
		return list;
	}

	public UserFolderVo getUserFolderById(int userId, int folderId) {
		return userFolderDao.getUserFolderById(userId, folderId);
	}
}
