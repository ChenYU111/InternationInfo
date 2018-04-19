package com.internation.info.well;



import java.io.Serializable;

public class UserQo implements Serializable {

    private static final long serialVersionUID = -4815652578119718069L;

    private Long roleId;// 角色id

    private String realName;// 姓名

    private String userName;// 账户名

    private UserStatus status;// 状态

    private Long userId;

    private Long[] userIdArr;

    private String password;// 密码

    private Long[] roleIds;// 用户拥有的角色id集合

    private boolean haveUserListPermission;// 是否拥有查看用户列表权限

    private Long[] appIdArr;

    private Long appId;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long[] getAppIdArr() {
        return appIdArr;
    }

    public void setAppIdArr(Long[] appIdArr) {
        this.appIdArr = appIdArr;
    }

    public Long[] getUserIdArr() {
        return userIdArr;
    }

    public void setUserIdArr(Long[] userIdArr) {
        this.userIdArr = userIdArr;
    }

    public boolean getHaveUserListPermission() {
        return haveUserListPermission;
    }

    public void setHaveUserListPermission(boolean haveUserListPermission) {
        this.haveUserListPermission = haveUserListPermission;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

}
