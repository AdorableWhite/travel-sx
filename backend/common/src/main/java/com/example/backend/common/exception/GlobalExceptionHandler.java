package com.example.backend.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.example.backend.common.result.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public R<Void> handlerNotLoginException(NotLoginException nle) {
        // Different types of NotLoginException
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "Token Invalid";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "Token Invalid";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "Token Timeout";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "Token Replaced";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "Kicked Out";
        } else {
            message = "Not Login";
        }
        return R.fail(401, message);
    }

    @ExceptionHandler(NotPermissionException.class)
    public R<Void> handlerNotPermissionException(NotPermissionException e) {
        return R.fail(403, "No Permission");
    }

    @ExceptionHandler(NotRoleException.class)
    public R<Void> handlerNotRoleException(NotRoleException e) {
        return R.fail(403, "No Role");
    }

    @ExceptionHandler(Exception.class)
    public R<Void> handlerException(Exception e) {
        e.printStackTrace(); 
        return R.fail(e.getMessage());
    }
}
