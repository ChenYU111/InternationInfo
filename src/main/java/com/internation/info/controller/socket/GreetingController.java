package com.internation.info.controller.socket;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {    
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;    
    @RequestMapping("/helloSocket")    
    public String index(){        
        return "/hello/index";    
    }    
   /* @MessageMapping("/change-notice")    
    public void greeting(String value){
        this.simpMessagingTemplate.convertAndSend("/topic/notice", value);    
    }*/
    
    @MessageMapping("/change-notice")
    @SendTo("/topic/notice")
    public String greeting(String value) {    
        return value;
    }
}


