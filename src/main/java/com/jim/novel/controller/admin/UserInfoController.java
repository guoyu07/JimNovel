package com.jim.novel.controller.admin;

import com.jim.novel.constant.SystemConstant;
import com.jim.novel.controller.BaseController;
import com.jim.novel.model.Admin;
import com.jim.novel.model.Response;
import com.jim.novel.model.User;
import com.jim.novel.service.UserService;
import com.jim.novel.utils.FileUtils;
import com.jim.novel.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public String uploadImg(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
        Response response = null;
        String fileName = file.getOriginalFilename();
        if(file==null){
            response = new Response(Response.ERROR_SERVER_500,"上传失败",null);
            return response.toJsonString();
        }
        int index = fileName.lastIndexOf(".");
        //图片后缀
        String extensions = fileName.substring(index);
//        if (!(fileName.endsWith("png") || !fileName.endsWith("jpeg") || fileName.endsWith("jpg") || fileName.endsWith("gif"))) {
//            response = new Response(Response.ERROR_FORBIDDEN_400,"图片格式错误",null);
//            return response.toJsonString();
//        }
        String uploadPath = HttpUtils.getRealPath() + SystemConstant.UPLOAD_FOLDER;
        String newFileName = System.currentTimeMillis() + extensions;
        String filePath = "";

        try {
             filePath = FileUtils.saveFile(file, uploadPath.toString(), newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            response = new Response(Response.ERROR_SERVER_500,"上传失败",null);
            return response.toJsonString();
        }

        User user = me();
        User nowUser = userService.getUserInfo(user.getId());
        nowUser.setAvaterUrl("/upload/"+newFileName);
        userService.updateUserInfoById(nowUser);
        request.getSession().setAttribute(SystemConstant.SESSION_USER,
                nowUser);

        response = new Response(Response.OK_200,"上传成功",null);
        return response.toJsonString();

    }


    @RequestMapping("changeInfo.htm")
    @ResponseBody
    public String changeInfo(HttpServletRequest request){
        String role = request.getParameter("role");
        String email="",name="";
        Response response = null;

        if("0".equals(role)){
            User me = me();
            me.setEmail(request.getParameter("email"));
            me.setName(request.getParameter("name"));
            if(userService.updateUserInfoById(me)){
                SessionedUser(me);
                response = new Response(Response.OK_200,"更新成功",null);
                return response.toJsonString();
            }

        }else {
            Admin me = admin();
            me.setName(request.getParameter("name"));
            if( userService.updateAdminInfoById(me)){
                SessionedAdmin(me);
                response = new Response(Response.OK_200,"更新成功",null);
                return response.toJsonString();
            }

        }
        response = new Response(Response.ERROR_FORBIDDEN_400,"更新异常",null);
        return response.toJsonString();

    }

    @RequestMapping(value = "changePwd.htm",method = RequestMethod.POST)
    @ResponseBody
    public String changePwd(HttpServletRequest request){
        Response response = null;
        try {
            String role = request.getParameter("role");
            String oldPwd="",newPwd="";

            if("0".equals(role)){
                User me = me();
                oldPwd = request.getParameter("password");
                if(oldPwd.equals(me.getPassword())){
                    me.setPassword(request.getParameter("newpassword"));
                    if(userService.updateUserInfoById(me)) {
                        SessionedUser(me);
                        response = new Response(Response.OK_200,"修改成功",null);
                        return response.toJsonString();
                    }else{
                        response = new Response(Response.OK_200,"修改失败",null);
                        return response.toJsonString();
                    }
                }else{
                    response = new Response(Response.OK_200,"密码错误",null);
                    return response.toJsonString();
                }
            }else {
                Admin me = admin();
                oldPwd = request.getParameter("password");
                if(oldPwd.equals(me.getPassword())){
                    me.setPassword(request.getParameter("newpassword"));
                    if(userService.updateAdminInfoById(me)) {
                        SessionedAdmin(me);
                        response = new Response(Response.OK_200,"修改成功",null);
                        return response.toJsonString();
                    }else{
                        response = new Response(Response.OK_200,"修改失败",null);
                        return response.toJsonString();
                    }
                }else{
                    response = new Response(Response.ERROR_FORBIDDEN_400,"密码错误",null);
                    return response.toJsonString();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            response = new Response(Response.ERROR_FORBIDDEN_400,"密码错误",null);
            return response.toJsonString();
        }




    }
}
