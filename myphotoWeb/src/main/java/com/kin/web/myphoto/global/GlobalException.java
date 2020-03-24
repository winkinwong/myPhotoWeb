package com.kin.web.myphoto.global;


import com.kin.web.myphoto.exception.*;
import com.kin.web.myphoto.pc.accountManager.entity.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);
    @ExceptionHandler
    @ResponseBody
    public ResultBean userException(UserException u, HttpServletRequest request , HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResultBean.error(u.getCode(),u.getMessage());
    }
    @ExceptionHandler
    @ResponseBody
    public ResultBean functionException(FunctionException u, HttpServletRequest request , HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResultBean.error(u.getCode(),u.getMessage());
    }
    @ExceptionHandler
    @ResponseBody
    public ResultBean roleException(RoleException u, HttpServletRequest request , HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResultBean.error(u.getCode(),u.getMessage());
    }
    @ExceptionHandler
    @ResponseBody
    public ResultBean roleFunctionException(RoleFunctionException u, HttpServletRequest request , HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResultBean.error(u.getCode(),u.getMessage());
    }
    @ExceptionHandler
    @ResponseBody
    public ResultBean imagesException(ImagesException u, HttpServletRequest request , HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResultBean.error(u.getCode(),u.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    public ResultBean unKnowException(Exception e){
        return ResultBean.error(504,"服务故障");
    }
    @ExceptionHandler
    @ResponseBody
    public ResultBean unKnowRuntimeException(RuntimeException r){

        return ResultBean.error(505,"服务异常");
    }
    @ExceptionHandler
    @ResponseBody
    public ResultBean methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            String field = fieldError.getField();
            String errorMsg = fieldError.getDefaultMessage();

            return ResultBean.error(201, errorMsg);
        }

        return ResultBean.error(201, "参数错误");
    }

}
