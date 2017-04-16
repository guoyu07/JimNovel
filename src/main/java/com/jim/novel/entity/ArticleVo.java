package com.jim.novel.entity;


import com.jim.novel.model.Article;
import com.jim.novel.model.Folder;
import com.jim.novel.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ArticleVo extends Article {

	private Folder folder;

	private List<FolderVo> folderPathList;

	private String pictureUrl;

	private String author;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPictureUrl() {
		if (StringUtils.isBlank(this.getPictureUrl())) {
			return "upload/blank.jpg";
		} else {
			return this.getPictureUrl();
		}
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<FolderVo> getFolderPathList() {
		return folderPathList;
	}


	public void setFolderPathList(List<FolderVo> folderPathList) {
		this.folderPathList = folderPathList;
	}
}
