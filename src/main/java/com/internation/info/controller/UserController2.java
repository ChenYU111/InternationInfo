package com.internation.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.internation.info.model.User;
import com.internation.info.service.UserService;
import com.internation.info.util.CommonDate;
import com.internation.info.util.NetUtil;
import com.internation.info.util.UploadUtil;
import com.internation.info.util.WordDefined;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * NAME   :  WebChat/com.amayadream.webchat.controller
 * Author :  Amayadream
 * Date   :  2016.01.09 17:56
 * TODO   :  用户控制器
 */
@Controller
public class UserController2 {
    @Resource private User user;
    @Resource private UserService userService;
   
    /**
     * 聊天主页
     */
    @RequestMapping("/chat")
    public String getIndex(){
        return "chat/index";
    }

    /**
     * 显示个人信息页面
     */
    /*@RequestMapping(value = "{userid}", method = RequestMethod.GET)
    public ModelAndView selectUserByUserid(@PathVariable("userid") Integer userid){
        ModelAndView view = new ModelAndView("information");
        user = userService.findUserByPKId(userid);
        view.addObject("user", user);
        return view;
    }*/

    /**
     * 显示个人信息编辑页面
     * @param userid
     * @param sessionid
     * @return
     */
   /* @RequestMapping(value = "{userid}/config")
    public ModelAndView setting(@PathVariable("userid") Integer userid){
        ModelAndView view = new ModelAndView("info-setting");
        user = userService.findUserByPKId(userid);
        view.addObject("user", user);
        return view;
    }*/

    /**
     * 更新用户信息
     * @param userid
     * @param sessionid
     * @param user
     * @return
     */
   @RequestMapping(value = "{userid}/update", method = RequestMethod.POST)
    public String  update(@PathVariable("userid") Integer userid, @ModelAttribute("userid") String sessionid, User user, RedirectAttributes attributes,
                          NetUtil netUtil,  CommonDate date, WordDefined defined, HttpServletRequest request){
        int flag = userService.update(user);
        if(flag>0){
            attributes.addFlashAttribute("message", "["+userid+"]资料更新成功!");
        }else{
            attributes.addFlashAttribute("error", "["+userid+"]资料更新失败!");
        }
        return "redirect:/{userid}/config";
    }

    /**
     * 修改密码
     * @param userid
     * @param oldpass
     * @param newpass
     * @return
     */
    /*@RequestMapping(value = "{userid}/pass", method = RequestMethod.POST)
    public String changePassword(@PathVariable("userid") Integer userid, String oldpass, String newpass, RedirectAttributes attributes,
                                 NetUtil netUtil, CommonDate date, WordDefined defined, HttpServletRequest request){
        user = userService.findUserByPKId(userid);
        if(oldpass.equals(user.getPassword())){
            user.setPassword(newpass);
            int flag = userService.update(user);
            if(flag>0){
                attributes.addFlashAttribute("message", "["+userid+"]密码更新成功!");
            }else{
                attributes.addFlashAttribute("error", "["+userid+"]密码更新失败!");
            }
        }else{
            attributes.addFlashAttribute("error", "密码错误!");
        }
        return "redirect:/{userid}/config";
    }
*/
    /**
     * 头像上传
     * @param userid
     * @param file
     * @param request
     * @return
     */
   /* @RequestMapping(value = "{userid}/upload")
    public String upload(@PathVariable("userid") Integer userid, MultipartFile file, HttpServletRequest request, UploadUtil uploadUtil,
                         RedirectAttributes attributes, NetUtil netUtil, CommonDate date, WordDefined defined){
        try{
            String fileurl = uploadUtil.upload(request, "upload", userid);
            user = userService.findUserByPKId(userid);
            int flag = userService.update(user);
            if(flag>0){
                attributes.addFlashAttribute("message", "["+userid+"]头像更新成功!");
            }else{
                attributes.addFlashAttribute("error", "["+userid+"]头像更新失败!");
            }
        } catch (Exception e){
            attributes.addFlashAttribute("error", "["+userid+"]头像更新失败!");
        }
        return "redirect:/{userid}/config";
    }*/

  /**
     * 获取用户头像
     * @param userid
     *//*
     /*  @RequestMapping(value = "{userid}/head")
    public void head(@PathVariable("userid") int userid, HttpServletRequest request, HttpServletResponse response){
        try {
            user = userService.findUserByPKId(userid);
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            response.setContentType("image/jpeg; charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            outputStream = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
