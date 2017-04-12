package com.jim.novel.controller.admin;

import com.jim.novel.controller.BaseController;
import com.jim.novel.model.Response;
import com.jim.novel.model.User;
import com.jim.novel.service.UserService;
import com.jim.novel.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息管理
 *
 * @author run
 * @create 2017-04-13
 **/
@Controller
@RequestMapping("/info")
public class UserInfoController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "uploadImg.htm",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam(value = "file", required = false) MultipartFile file, Integer userId){
        Response response = null;
        User user = userService.getUserInfo(userId);
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        //图片后缀
        String extensions = fileName.substring(index);
        logger.info(fileName);
        logger.info(extensions);
//        if (!(fileName.endsWith("png") || !fileName.endsWith("jpeg") || fileName.endsWith("jpg") || fileName.endsWith("gif"))) {
//            response = new Response(Response.ERROR_FORBIDDEN_400,"图片格式错误",null);
//            return response.toJsonString();
//        }
        StringBuffer path = new StringBuffer();
        path.append("/WEB-INF/static/upload");
        String filePath = "";
        try {
             filePath = FileUtils.saveFile(file, path.toString(), "avater" + extensions);
        } catch (IOException e) {
            e.printStackTrace();
            response = new Response(Response.ERROR_SERVER_500,"上传失败",null);
            return response.toJsonString();
        }
        Map result = new HashMap<>();
        result.put("url",filePath);
        response = new Response(Response.OK_200,"上传成功",result);
        return response.toJsonString();

    }


    @RequestMapping("changeInfo.htm")
    @ResponseBody
    public String changeInfo(String name,String email){
        Response response = null;


        response = new Response(Response.OK_200,"更新成功",null);
        return response.toJsonString();

    }

    @RequestMapping("changePwd.htm")
    @ResponseBody
    public String changePwd(String pwd1,String pwd2){
        Response response = null;


        response = new Response(Response.OK_200,"修改密码成功",null);
        return response.toJsonString();

    }
}
