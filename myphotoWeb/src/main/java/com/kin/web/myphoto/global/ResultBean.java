package com.kin.web.myphoto.global;

public class ResultBean<T> {
    private int code;
    private String message;
    private T data;
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ResultBean() {
    }

    public ResultBean(String requestId) {
        this.requestId = requestId;
    }

    public ResultBean(String message, String requestId) {
        this.message = message;
        this.requestId = requestId;
    }

    public ResultBean(int code, String message, String requestId) {
        this.code = code;
        this.message = message;
        this.requestId = requestId;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public static ResultBean error(int code , String message){
        ResultBean resultBean = new ResultBean(code,message,ThreadLocalHelper.getRequestId());
        return resultBean;
    }
    public static ResultBean success(){
        return new ResultBean(ThreadLocalHelper.getRequestId());
    }
    public static ResultBean success(String message){
        return new ResultBean(message,ThreadLocalHelper.getRequestId());
    }
    public static <V> ResultBean<V> success(V data){
        ResultBean resultBean = new ResultBean(ThreadLocalHelper.getRequestId());
        resultBean.setData(data);
        return resultBean;
    }
}
