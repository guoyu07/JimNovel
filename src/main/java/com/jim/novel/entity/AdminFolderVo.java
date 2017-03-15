package com.jim.novel.entity;


import com.jim.novel.model.AdminFolder;

public class AdminFolderVo extends AdminFolder {

	private FolderVo folder;

	public FolderVo getFolder() {
		return folder;
	}

	public void setFolder(FolderVo folder) {
		this.folder = folder;
	}

}
