package com.pzz.review.exception;

import com.pzz.review.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseDTO<String> handleAppException(AppException e) {
        return new ResponseDTO<>(0, e.getMessage(), null);
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseDTO<String> handleUnknownException(Exception e) {
//        return new ResponseDTO<>(-99, e.getMessage(), null);
//    }
}
