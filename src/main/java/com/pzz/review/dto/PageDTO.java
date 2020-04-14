package com.pzz.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDTO<T> {
    private List<T> data;

    private long count;
}
