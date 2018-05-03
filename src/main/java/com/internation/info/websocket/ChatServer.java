package com.internation.info.websocket;

import com.alibaba.fastjson.JSONObject;
import com.internation.info.model.Message;
import com.internation.info.model.User;
import com.internation.info.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author :  Amayadream
 * @date :  2017.06.21 22:02
 */
@ServerEndpoint(value = "/chatServer", configurator = HttpSessionConfigurator.class)
public class ChatServer {
	@Autowired
	UserService userService;
    private static CopyOnWriteArraySet<ChatServer> websocketSet = new CopyOnWriteArraySet<>();  //持有websocket对象

    private Session session;    //会话
    private String userId;      //用户名
    private HttpSession httpSession;    //session

    private static List<String> list = new ArrayList<String>();   //在线列表,记录用户名称
    private static Map<String, Session> routeTab = new HashMap<>();  //用户名和websocket的session绑定的路由表

    /**
     * 连接建立
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config,HttpServletRequest req){
        this.session = session;
        websocketSet.add(this);
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        int uId = (int)req.getSession().getAttribute("userId");
        User user = userService.findUserByPKId(uId);
        this.userId = user.getUserName();
        list.add(userId);
        routeTab.put(userId, session);
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(){
        websocketSet.remove(this);
        list.remove(userId);
        routeTab.remove(userId);
    }

    /**
     * 收到消息
     */
    @OnMessage
    public void onMessage(String data) {
        Message message = JSONObject.parseObject(data, Message.class);
        singleSend(data, routeTab.get(userId));
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Throwable error){
        error.printStackTrace();
    }

    /**
     * 对特定用户发送消息
     */
    public void singleSend(String message, Session session){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
