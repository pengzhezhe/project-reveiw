package com.pzz.review.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class CommonUtils {
    public static <T, K> void copyListProperties(List<T> source, List<K> target, Class<K> tClass) throws Exception {
        for (T e : source) {
            K k = tClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(e, k);
            target.add(k);
        }
    }
}
