package com.jim.novel.service;

import com.jim.novel.constant.FolderConstant;
import com.jim.novel.dao.FolderMapper;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Folder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by run on 17/3/14.
 */
@Service()
public class FolderService {
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private FolderMapper folderDao;
    


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

    // ///////////////////////////////
    // ///// 修改 ////////
    // ///////////////////////////////

    /**
     * 更新目录
     *
     * @param folderId
     * @param fatherId
     * @param ename
     * @param name
     * @param status
     * @param type
     * @param sort
     * @return folder
     */
    @CacheEvict(value = "folder", allEntries = true)
    public void updateFolderById(int folderId, String name, String ename,
                                 FolderConstant.status status, String content, int height, int width) {
        folderDao.updateFolderById(folderId, name, ename, status, content,
                height, width);
    }

    /**
     * 通过指定Id修改其目录的序列
     *
     * @param folderId
     * @param sort
     * @return Integer
     */
    @CacheEvict(value = "folder", allEntries = true)
    public int updateSort(int folderId, int sort) {
        return folderDao.updateSort(folderId, sort);
    }



    /**
     * @param folderId
     * @param count
     * @return
     */
    public int updateCount(int folderId, int count) {
        return folderDao.updateCount(folderId, count);
    }

    // ///////////////////////////////
    // ///// 查詢 ////////
    // ///////////////////////////////

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
     * 得到所有子目录
     *
     * @param fatherId
     * @return List<Folder>
     */
    @Cacheable(value = "folder")
    public List<FolderVo> getFolderListByFatherId(int fatherId,
                                                  FolderConstant.status status) {
        return folderDao.getFolderListByFatherId(fatherId, status);
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


    public int firstFolderId(int folderId) {
        FolderVo folder = folderDao.selectByPrimaryKey(folderId);
        String[] folderIdList = folder.getPath().split("#");
        return Integer.parseInt(folderIdList[0]);
    }
}
