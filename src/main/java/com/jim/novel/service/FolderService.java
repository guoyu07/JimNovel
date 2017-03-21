package com.jim.novel.service;

import com.jim.novel.constant.enums.FolderDisplay;
import com.jim.novel.dao.FolderMapper;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Folder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by run on 17/3/14.
 */
@Service
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

    public int firstFolderId(int folderId) {
        FolderVo folder = folderDao.selectByPrimaryKey(folderId);
        String[] folderIdList = folder.getPath().split("#");
        return Integer.parseInt(folderIdList[0]);
    }
}
