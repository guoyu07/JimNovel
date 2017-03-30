package com.jim.novel.controller;


import com.alibaba.fastjson.JSON;
import com.jim.novel.constant.SystemConstant;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.entity.JsonVo;
import com.jim.novel.model.Folder;
import com.jim.novel.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
     * @param  pageNum
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

    @ResponseBody
    @RequestMapping(value = "/addCollection.htm", produces = "text/html;charset=UTF-8")
    public String addShelf(@RequestParam(value = "articleId")int articleId, HttpServletRequest request){
        HttpSession session = request.getSession();
        User me = (User) session.getAttribute(SystemConstant.SESSION_USER);
        JsonVo<String> json = new JsonVo<String>();
        if(me==null){
            json.setErrorMsg("请先登录！");
            return JSON.toJSONString(json);
        }
        if (userCollectService.isCollected(me,articleId)){
            json.setErrorMsg("您已收藏过了，请勿重复添加！");
           return JSON.toJSONString(json);
        }
        userCollectService.add(me,articleId);
        json.setSuccessMsg("添加成功!");
        return JSON.toJSONString(json);
    }

}