package com.internation.info.controller;

import com.alibaba.fastjson.JSONObject;
import com.internation.info.common.ResultConstant;
import com.internation.info.common.Results;
import com.internation.info.model.Group;
import com.internation.info.model.GroupMember;
import com.internation.info.model.Relation;
import com.internation.info.model.User;
import com.internation.info.service.GroupMemberService;
import com.internation.info.service.GroupService;
import com.internation.info.service.RelationService;
import com.internation.info.vo.FriendVo;
import com.internation.info.vo.GroupVo;
import com.internation.info.vo.MemberVo;
import com.internation.info.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :  Amayadream
 * @date :  2017.06.20 22:@SessionAttributes({Constants.SESSION_USERID, Constants.SESSION_USER})
23
 */
@RestController
@RequestMapping(value = "")
public class ChatController {

    @Autowired
    private GroupService groupService;
    @Resource
    private GroupMemberService groupMemberService;
    @Resource
    private RelationService relationService;

    /**
     * 获取好友列表/个人信息/群组列表
     */
    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public Results friend( int userId,  User user) {
        List<Group> groups = groupService.list(new Group(userId));
        //List<Relation> relations = relationService.list(new Relation(userId));
        JSONObject data = new JSONObject();
        data.put("group", GroupVo.convertToVos(groups));
        data.put("mine", UserVo.convert(user));
        //data.put("friend", FriendVo.convertToVo(relations));
        return Results.ok(ResultConstant.SUCCESS, data);
    }

    /**
     * 获取指定群的群成员列表
     */
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public Results members( int userId,  User user, @RequestParam int groupId) {
        List<GroupMember> list = groupMemberService.list(groupId);
        JSONObject data = new JSONObject();
        data.put("owner", MemberVo.convertToVo(user));
        data.put("members", list.size());
        data.put("list", MemberVo.convertToVos(list));
        return Results.ok(ResultConstant.SUCCESS, data);
    }

}
