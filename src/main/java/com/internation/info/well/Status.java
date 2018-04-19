package com.internation.info.well;



/**
 * 基本状态
 * 
 * 
 * 创建日期：2016年8月3日 下午1:32:52 操作用户：zhoubang
 *
 */
public enum Status implements BaseEntityEnum<Status> {

    ENABLE(0, "启用"), DISABLE(1, "禁用");

    private Status(int code, String description) {
        this.code = new Integer(code);
        this.description = description;
    }

    private int code;

    private String description;

    public int getCode() {

        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getIntValue() {
        return this.code;
    }
}
