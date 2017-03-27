package com.jim.novel.controller;

import com.jim.novel.constant.SystemConstant;
import com.jim.novel.entity.JsonVo;
import com.jim.novel.exception.TemplateNotFoundException;
import com.jim.novel.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-03-27 21:46
 **/
@Controller
@RequestMapping("center")
public class LoginController extends BaseController {


    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String login(@RequestParam(value = "p", defaultValue = "1") long p,
                           ModelMap modelMap) {
        modelMap.put("g_folderId",p);
        return "/template/blog/login";
    }


    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String adminLogout(HttpServletRequest request,@RequestParam(value = "p", defaultValue = "1") long p,ModelMap modelMap) throws TemplateNotFoundException {
        request.getSession().removeAttribute(SystemConstant.SESSION_USER);
        return "redirect:" + "/";
    }

    @ResponseBody
    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public JsonVo<String> adminLogin(@RequestParam(value = "email") String email,
                                     @RequestParam(value = "password") String password,
                                     HttpServletRequest request, ModelMap modelMap) {
        JsonVo<String> json = new JsonVo<String>();
        try {
            if (StringUtils.isBlank(password)) {
                json.getErrors().put("password", "密码不能为空");
            } else if (password.length() < 6 && password.length() > 20) {
                json.getErrors().put("password", "密码最少6个字符，最多20个字符");
            }
            json.check();

            userService.Authlogin(email, password, request);


        } catch (Exception e) {
//            // 异常，重置验证码
//            request.getSession().removeAttribute(
//                    com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            json.setResult(false);
            json.getErrors().put("password", "邮箱或密码错误");
        }
        return json;
    }
}
