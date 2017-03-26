package com.jim.novel.controller;

import com.jim.novel.entity.FolderVo;
import com.jim.novel.model.Folder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**目录控制器
 * Created by run on 17/3/13.
 */
@Controller
@RequestMapping("/folder")
public class FolderController extends BaseController {

    /**
     * 目录ID
     *
     * @param ename
     * @param pageNum
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/{folderId}.htm", method = RequestMethod.GET)
    public String folder(@PathVariable int folderId,
                         @RequestParam(value = "p", defaultValue = "1") int p,
                         ModelMap modelMap) {
        try {
            Folder folder = folderService.getFolderById(folderId);
            modelMap.put("folderId", folderId);
            FolderVo fatherFolder = folderService.getFolderById(folderService.firstFolderId(folderId));
            modelMap.put("g_folderId", fatherFolder.getFolderId());
            modelMap.put("p", p);
            return themeService.getFolderTemplate(folder.getFolderId());
        } catch (Exception e) {
            modelMap.addAttribute("g_folderId", 0);
            logger.fatal(e.getMessage());
            return themeService.get404();
        }
    }
}