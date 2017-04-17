package com.jim.novel.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.jim.novel.constant.SystemConstant;
import com.jim.novel.entity.JsonVo;
import com.jim.novel.exception.TemplateNotFoundException;
import com.jim.novel.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * 登录注册控制器
 *
 * @author
 * @create 2017-03-27 21:46
 **/
@Controller
@RequestMapping("center")
public class LoginController extends BaseController {

    /**
     * Kaptcha 验证码
     */
    @Autowired
    private DefaultKaptcha captchaProducer;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String login(@RequestParam(value = "p", defaultValue = "1") long p,
                           ModelMap modelMap) {
        modelMap.put("g_folderId",p);
        return "/template/blog/login";
    }


    @RequestMapping(value = "/register.htm", method = RequestMethod.GET)
    public String register(@RequestParam(value = "p", defaultValue = "1") long p,
                        ModelMap modelMap) {
        modelMap.put("g_folderId",p);
        return "/template/blog/register";
    }


    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String adminLogout(HttpServletRequest request,@RequestParam(value = "p", defaultValue = "1") long p,ModelMap modelMap) throws TemplateNotFoundException {
        request.getSession().removeAttribute(SystemConstant.SESSION_USER);
        return "redirect:" + "/";
    }



    @ResponseBody
    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public JsonVo<String> login(@RequestParam(value = "email") String email,
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
            json.setResult(false);
            json.getErrors().put("password", "邮箱或密码错误");
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public JsonVo<String> register(@RequestParam(value = "email") String email,
                                     @RequestParam(value = "password") String password,@RequestParam(value = "checkpassword")String checkpassword,
                                        @RequestParam(value = "captcha")String captcha,
                                     HttpServletRequest request, ModelMap modelMap) {
        JsonVo<String> json = new JsonVo<String>();
        try {

            String kaptcha = (String) request.getSession().getAttribute(
                    com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

            // 校验验证码
            if (StringUtils.isNotBlank(kaptcha)
                    && kaptcha.equalsIgnoreCase(captcha)) {

            } else {
                json.setErrorMsg("验证码错误");
            }

            if (StringUtils.isBlank(password)||StringUtils.isBlank(checkpassword)) {
                json.setErrorMsg("密码不能为空");
            } else if (password.length() < 6 && password.length() > 20) {
                json.setErrorMsg("密码最少6个字符，最多20个字符");
            }

            if(!password.equals(checkpassword)){
                json.setErrorMsg("请检查两次密码是否输入一致！");
            }
            json.check();

            if(userService.register(email, password)){
                json.setSuccessMsg("注册成功！");
            }else {
                json.setErrorMsg("邮箱已经存在，请直接登录！");
            }


        } catch (Exception e) {
            // 异常，重置验证码
            request.getSession().removeAttribute(
                    com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            e.printStackTrace();
            json.setMsg("change_captcha");
        }
        return json;
    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "captcha.htm", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        logger.info( "capText="+capText);
        request.getSession().setAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
