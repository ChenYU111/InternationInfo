package com.internation.info.well;



import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 创建日期：2016年8月3日 下午3:38:08 操作用户：zhoubang
 * 
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = 851019162980632315L;

    private Long userId;
    private String userName;// 账户名
    private String realName;// 真实姓名
    private String roleName;// 所属角色名称
    private UserStatus status;// 账户状态
    
    @SuppressWarnings("unused")
    private String statusName;// 账户状态描述

    @SuppressWarnings("unused")
    private String fromSystem;// 来源系统名称



    public String getFromSystem() {
        return this.fromSystem;
    }

    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }

    private Date createTime;

    private Date updateTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusName() {
        return status.getDescription();
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
