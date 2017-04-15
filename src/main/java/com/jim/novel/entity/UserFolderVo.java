package com.jim.novel.entity;


import com.jim.novel.model.UserFolder;

public class UserFolderVo extends UserFolder {

	private FolderVo folder;

	public FolderVo getFolder() {
		return folder;
	}

	public void setFolder(FolderVo folder) {
		this.folder = folder;
	}

}
