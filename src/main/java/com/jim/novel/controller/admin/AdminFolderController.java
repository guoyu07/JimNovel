package com.jim.novel.controller.admin;

import com.jim.novel.constant.ArticleConstant;
import com.jim.novel.constant.enums.ArticleStatus;
import com.jim.novel.constant.enums.FolderDisplay;
import com.jim.novel.controller.BaseController;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Article;
import com.jim.novel.model.Chapter;
import com.jim.novel.model.Response;
import com.jim.novel.model.User;
import com.jim.novel.service.FolderService;
import com.jim.novel.utils.RegexUtils;
import com.jim.novel.utils.SSUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 小说分类信息控制器
 *
 * @author run
 * @create 2017-04-12
 **/
@Controller
@RequestMapping("/fo")
public class AdminFolderController extends BaseController {

    @Autowired
    private FolderService folderService;


    /**
     * 小说分类
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String getFolderList(){
        List<FolderVo> list = folderService.getAllFolderList();

        //返回的json数据统一封装到Response这个对象中 具体包括code,msg,data
        //ajax异步请求的时候，需要从data取出对应的对象
       return renderSuccess(list);
    }









    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "fatherId", defaultValue = "0") Integer fatherId,
                      @RequestParam(value = "folderName") String folderName,
                      @RequestParam(value = "folderEname") String folderEname,
                      @RequestParam(value = "display") String display,
                      @RequestParam(value = "content") String content,
                      @RequestParam(value = "img", required = false) MultipartFile img){
        if (StringUtils.isBlank(folderName)) {
            return renderError("分类名称不能为空");
        }
        if (StringUtils.isBlank(folderEname)) {
            return renderError("英文名称不能为空");
        } else if (!RegexUtils.isAlphaUnderline(folderEname)) {
            return renderError("只能是英文字母，数字和下划线");
        } else if (StringUtils.isBlank(content)) {
            return renderError("分类详情不能为空");
        }else if (folderService.isFolderByEname(folderEname)) {
            return renderError("英文名称不能重复");
        }
        try {
            folderService.addFolder(fatherId,
                    SSUtils.toText(folderName.trim()),
                    SSUtils.toText(folderEname.toLowerCase().trim()),display, SSUtils.toText(content.trim()),img);
        } catch (FolderNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return renderSuccess(null);
    }

}
