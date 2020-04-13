package com.pzz.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一返回值
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private int code;

    private String msg;

    private T data;
}
