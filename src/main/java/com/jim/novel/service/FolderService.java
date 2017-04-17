package com.jim.novel.service;

import com.jim.novel.constant.enums.FolderDisplay;
import com.jim.novel.dao.FolderMapper;
import com.jim.novel.entity.UserFolderVo;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Folder;
import com.jim.novel.utils.MediaUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by run on 17/3/14.
 */
@Service
public class FolderService {
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private FolderMapper folderDao;
    
    @Autowired
    private UserFolderService userFolderService;



    // ///////////////////////////////
    // ///// 刪除 ////////
    // ///////////////////////////////

    /**
     * 删除目录
     *
     * @param folderId
     * @return boolean
     */
    @CacheEvict(value = "folder", allEntries = true)
    public boolean deleteFolderById(int folderId) {
        return folderDao.deleteByPrimaryKey(folderId);
    }


    /**
     * 得到目录的Path
     *
     * @param folderId
     * @return
     * @throws FolderNotFoundException
     */
    @Cacheable(value = "folder")
    public List<FolderVo> getFolderPathListByFolderId(int folderId)
            throws FolderNotFoundException {
        List<FolderVo> list = new ArrayList<FolderVo>();
        if (folderId == 0) {
            return list;
        } else {
            Folder folder = this.getFolderById(folderId);
            String[] str = folder.getPath().split("#");
            for (int i = 0; i < folder.getLevel(); i++) {
                FolderVo fold = this.getFolderById(Integer.parseInt(str[i]));
                list.add(fold);
            }
            return list;
        }
    }


    /**
     * 得到所有的四层目录
     *
     * @return
     * @throws FolderNotFoundException
     */
    @Cacheable(value = "folder")
    public List<FolderVo> getAllFolderList() {
        List<FolderVo> folderList = folderDao.getAllFolderList();
        HashMap<String, FolderVo> folderMap = new HashMap<String, FolderVo>();
        for (FolderVo folder : folderList) {
            folderMap.put(folder.getFolderId() + "", folder);
        }
        for (FolderVo folder : folderList) {
            folder.setPathName(getPathName(folderMap, folder.getPath()));
        }
        return folderList;
    }


    @Cacheable(value = "folder")
    public boolean isFolderByEname(String ename) {
        Folder folder = folderDao.getFolderByEname(ename);
        if (folder == null) {
            return false;
        } else {
            return true;
        }
    }


    @Cacheable(value = "folder")
    private String getPathName(HashMap<String, FolderVo> folderMap, String path) {
        List<String> names = new ArrayList<String>();
        try {
            String[] folderIds = path.split("#");
            for (String folderId : folderIds) {
                names.add(folderMap.get(folderId).getName());
            }
        } catch (NullPointerException e) {
            logger.fatal(path + " - " + StringUtils.join(path.split("#"), ","));
        }
        return StringUtils.join(names, " - ");
    }

    /**
     * 得到指定目录
     *
     * @param folderId
     * @return Folder
     * @throws FolderNotFoundException
     */
    @Cacheable(value = "folder")
    public FolderVo getFolderById(int folderId) throws FolderNotFoundException {
        FolderVo folder = folderDao.selectByPrimaryKey(folderId);
        if (folder == null) {
            throw new FolderNotFoundException("");
        } else {
            logger.debug("目录("+folderId+")中的图片尺寸："+folder.getWidth()+" x "+folder.getHeight());
            return folder;
        }
    }

    /**
     * 通过ename和fatherId获得目录
     *
     * @param ename
     * @param fatherId
     * @return
     * @throws FolderNotFoundException
     */
    @Cacheable(value = "folder")
    public Folder getFolderByEname(String ename) throws FolderNotFoundException {
        Folder folder = folderDao.getFolderByEname(ename);
        if (folder == null) {
            throw new FolderNotFoundException(ename + " 目录，不存在");
        } else {
            return folder;
        }
    }

    /**
     * 得到所有子目录
     *
     * @param fatherId
     * @return List<Folder>
     */
    @Cacheable(value = "folder")
    public List<FolderVo> getFolderListByFatherId(int fatherId,
                                                  FolderDisplay display) {
        return folderDao.getFolderListByFatherId(fatherId, display.getValue());
    }

    /**
     * 得到推荐分类
     *
     * @param fatherId
     * @return List<Folder>
     */
    @Cacheable(value = "folder")
    public List<FolderVo> getPopularFolderList() {
        return folderDao.getFolderListBySort();
    }

    /**
     * 获取当前id的顶级目录id
     * @param folderId
     * @return
     */
    public int firstFolderId(int folderId) {
        FolderVo folder = folderDao.selectByPrimaryKey(folderId);
        String[] folderIdList = folder.getPath().split("#");
        return Integer.parseInt(folderIdList[0]);
    }


    /**
     * 增加目录
     *
     * @param fatherId
     * @param name
     * @param ename
     * @param status
     * @param type
     * @return Folder
     * @throws FolderNotFoundException
     */
    @CacheEvict(value = "folder", allEntries = true)
    @Transactional
    public Folder addFolder(Integer fatherId, String name, String ename,
                            String display, String content, MultipartFile file)
            throws FolderNotFoundException, IOException {
        Folder folder = new Folder();
        folder.setFatherId(fatherId);
        folder.setHeight(0);
        folder.setWidth(0);
        if (fatherId == 0) {
            folder.setLevel(1);
        } else {
            Folder fatherFolder = this.getFolderById(fatherId);
            folder.setLevel(fatherFolder.getLevel() + 1);
        }
        String imgBig = "";
        if (file != null && !file.isEmpty()) {
            imgBig = MediaUtils.saveImage(file, folder.getWidth(),
                    folder.getHeight());
        }
        folder.setImgUrl(imgBig);
        folder.setName(name);
        folder.setEname(ename);
        folder.setContent(content);
        folder.setPath("");
        folder.setCount(0);
        folder.setSort(1);
        folder.setDisplay(display);
        folder.setCreateTime(new Date());
        folder.setModifyTime(new Date());
        folderDao.addFolder(folder);

        if (fatherId == 0) {
            this.updatePath(folder.getFolderId(), folder.getFolderId() + "");
        } else {
            Folder fatherFolder = this.getFolderById(fatherId);
            this.updatePath(folder.getFolderId(), fatherFolder.getPath() + "#"
                    + folder.getFolderId());
        }
        return folder;
    }

    /**
     * 通过指定Id修改其目录的路径
     *
     * @param folderId
     * @param path
     * @return Integer
     */
    public int updatePath(long folderId, String path) {
        return folderDao.updatePath(folderId, path);
    }
}
