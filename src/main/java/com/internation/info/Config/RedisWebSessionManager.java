package com.internation.info.Config;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RedisWebSessionManager extends DefaultWebSessionManager {
	 @Override  
	    protected void onStart(Session session, SessionContext context) {  
	        super.onStart(session, context);  
	        ServletRequest request = WebUtils.getRequest(context);  
	        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);  
	    }  
}
