package com.internation.info.vo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.internation.info.model.GroupMember;
import com.internation.info.model.User;

/**
 * @author :  Amayadream
 * @date :  2017.06.19 23:26
 */
public class MemberVo implements Serializable {

    private int id;
    private String username;
    private String avatar;
    private String sign;

    /**
     * members -> memberVos
     */
    public static List<MemberVo> convertToVos(List<GroupMember> members) {
        List<MemberVo> vos = new ArrayList<MemberVo>();
        if (members == null || members.size() == 0) {
            return vos;
        }
        members.forEach((item) -> vos.add(convertToVo(item)));
        return vos;
    }

    /**
     * member -> memberVo
     */
    public static MemberVo convertToVo(GroupMember member) {
        MemberVo vo = new MemberVo();
        vo.setId(member.getId());
        vo.setUsername(member.getMember());
        return vo;
    }

    /**
     * use -> member
     */
    public static MemberVo convertToVo(User user) {
        MemberVo vo = new MemberVo();
        vo.setId(user.getId());
        vo.setUsername(user.getUserName());
        return vo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
