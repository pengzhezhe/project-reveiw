package com.pzz.review.exception;

import com.pzz.review.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class AppExceptionHandler {
    /**
     * Service异常
     *
     * @param e 异常
     * @return response
     */
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseDTO<String> handleAppException(AppException e) {
        return new ResponseDTO<>(0, e.getMessage(), null);
    }

}
