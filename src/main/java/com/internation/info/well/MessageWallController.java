package com.internation.info.well;


import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internation.info.model.BasicMessageWall;
import com.internation.info.service.UserService;


import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 留言墙
 *
 */
@Controller
public class MessageWallController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasicMessageWallService basicMessageWallService;

    @Autowired
    private UserService userService;

    /**
     * 进入留言墙页面
     */
    @RequestMapping("/view")
    public String toChatView(ModelMap map) {
        int num = 50;
        //获取指定数量的留言列表
        List<BasicMessageWall> messageList = basicMessageWallService.getMessageList(num);
        List<BasicMessageWall> messageList2 = new ArrayList<>();
        int index =0 ;
        for (BasicMessageWall basicMessageWall : messageList) {
        	if(index<num){
        		index++;
        		messageList2.add(basicMessageWall);
        	}
		}
        map.put("messageList",messageList2);
        return "wall/wall";
    }

    /**
     * 新增留言消息
     * @param request
     * @param msg
     * @return
     */
    @ResponseBody
    @RequestMapping("/addWall")
	public AjaxResult<String> addMessage(HttpServletRequest request, BasicMessageWall msg) {
		AjaxResult<String> result = new AjaxResult<String>();
		if (StringUtils.isBlank(msg.getContent()) || "".equals(msg.getContent().replaceAll(" ", ""))) {
			result.setCode(10001);
			result.setMsg("请输入留言内容");
			return result;
		}
		try {
			msg.setCreateTime(new Date());
			msg.setUpdateTime(new Date());
			msg.setUserId((int) request.getSession().getAttribute("userId"));
			// 用户名为空，就指定当前登录的系统用户名
			if (msg.getUserName() == null || msg.getUserName().equals("")) {
				int uid = (int) request.getSession().getAttribute("userId");
				msg.setUserName(userService.getUserName(uid));
			}

			basicMessageWallService.save(msg);
		} catch (Exception e) {
			result.setCode(10002);
			result.setMsg("留言提交失败，请稍后重试");
			return result;
		}
		return result;
	}

  
}

