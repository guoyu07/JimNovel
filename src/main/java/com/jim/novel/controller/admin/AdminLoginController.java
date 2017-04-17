package com.jim.novel.controller.admin;

import com.jim.novel.constant.SystemConstant;
import com.jim.novel.controller.BaseController;
import com.jim.novel.entity.JsonVo;
import com.jim.novel.exception.AuthException;
import com.jim.novel.model.Admin;
import com.jim.novel.model.Response;
import com.jim.novel.model.User;
import com.jim.novel.utils.AuthUtils;
import com.jim.novel.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**管理后台登录控制器(用户和管理员均可登录)
 *
 *
 * @author run
 * @create 2017-04-11
 **/
@Controller
public class AdminLoginController extends BaseController{

    @ResponseBody
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String adminLogin(@RequestParam(value = "name") String name,
                                @RequestParam(value = "password") String password,
                                HttpServletRequest request, ModelMap modelMap) {

        Admin admin = userService.adminIsExist(name);
        User user = userService.userIsExist(name);
        if (admin==null&&user==null){
            request.getSession().removeAttribute(
                    com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            Response response = new Response(Response.ERROR_SOURCE_FAILED_404,"用户名不存在",null);
            return response.toJsonString();
        }

        //如果是管理员
        if(admin!=null){
            if (userService.checkPwd(admin.getName(), AuthUtils.getPassword(password))){
                //密码校验通过，保存session
                HttpSession session = request.getSession();
                admin.setPassword("");
                session.setAttribute(SystemConstant.SESSION_ADMIN,
                        admin);

            }else {
                Response response = new Response(Response.ERROR_FORBIDDEN_400,"管理员密码错误",null);
                return response.toJsonString();
            }

        }
        //如果是用户
        if(user!=null){
            try {
                userService.Authlogin(user.getEmail(),password,request);
            } catch (AuthException e) {
                e.printStackTrace();
                Response response = new Response(Response.ERROR_FORBIDDEN_400,"密码错误",null);
                return response.toJsonString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(user==null && admin==null){
            return renderError("用户名不存在");
        }
        Response response = new Response(Response.OK_200,"登录成功",null);
        return response.toJsonString();

    }

    @RequestMapping(value = "/adminLogout.htm", method = RequestMethod.GET)
    public String adminLogout(HttpServletRequest request){
        request.getSession().removeAttribute(SystemConstant.SESSION_USER);
        request.getSession().removeAttribute(SystemConstant.SESSION_ADMIN);
        return "redirect:/admin/index";

    }

}
