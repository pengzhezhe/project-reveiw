package com.pzz.review.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class CommonUtils {
    public static <T, K> void copyListProperties(List<T> source, List<K> target, Class<K> tClass) {
        for (T e : source) {
            K k = null;
            try {
                k = tClass.getDeclaredConstructor().newInstance();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            BeanUtils.copyProperties(e, k);
            target.add(k);
        }
    }
}
