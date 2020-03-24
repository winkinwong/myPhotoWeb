package com.kin.web.myphoto.exception;

public enum Error {
    USER_NOT_FOUND(10001,"用户不存在"),
    USER_EXISTS(10002,"用户已存在"),
    USER_LOGIN_FAIL(10003,"登录失败"),
    USER_LOGOUT(10004,"用户已经登出"),
    USER_ALREAD_LOGIN(10005,"用户已经登录"),
    USER_PASSWORD_FAIL(10006,"密码错误"),

    //权限********************************************
    FUNCTION_NOT_FOUND(20001,"未找到该权限"),
    FUNCTION_EXISTS(20002,"该权限名已存在"),
    FUNCTION_RELATION_EXISTS(20003,"该权限有存在关联角色"),

    //角色********************************************
    ROLE_EXISTS(30001,"角色名已存在"),
    ROLE_NOT_FOUND(30002,"该角色不存在"),

    //RoleFunction*************************************
    ROLEFUNCTION_IS_NULL(40001,"当前角色未添加权限"),
    ROLEFUNCTION_NOT_FOUND(40002,"当前角色未添加该权限"),

    //Images*******************************************
    IMG_UPLOAD_FAIL(50001,"图片上传失败"),
    IMG_SAVE_FILE(50002,"图片保存失败"),
    IMG_NOT_BELONG(50003,"该图片不属于你"),
    IMG_NOT_FOUND(50004,"图片不存在"),
    ;
    private int code;
    private String message;

    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
