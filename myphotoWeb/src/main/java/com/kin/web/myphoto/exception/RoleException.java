package com.kin.web.myphoto.exception;

public class RoleException extends RuntimeException {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RoleException(Error error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public RoleException(String message, Error error) {
        super(message);
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public RoleException(String message, Throwable cause, Error error) {
        super(message, cause);
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public RoleException(Throwable cause, Error error) {
        super(cause);
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public RoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Error error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = error.getCode();
        this.message = error.getMessage();
    }


    public RoleException() {
    }

    public RoleException(String message) {
        super(message);
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleException(Throwable cause) {
        super(cause);
    }

    public RoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
