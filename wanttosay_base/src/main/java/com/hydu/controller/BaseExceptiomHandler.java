package com.hydu.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
public class BaseExceptiomHandler {
    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        return new Result(false,StatusCode.ERROR,e.getMessage());
    }
}
