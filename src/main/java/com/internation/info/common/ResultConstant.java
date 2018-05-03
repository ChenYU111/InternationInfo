package com.internation.info.common;

/**
 * 返回状态码与返回信息对应实体
 * @author :  Amayadream
 * @date :  2017.06.12 22:18
 */
public enum ResultConstant {

    SUCCESS(0, "ok")

    , PERMISSION_ERROR(9999, "操作越权")
    ;

    Integer code;
    String message;

    ResultConstant(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
