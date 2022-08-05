package com.slipper.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 对象工具类
 * @author gumingchen
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 集合数据拷贝
     * @param sources 数据源数组
     * @param target 对象
     * @return
     */
    public static<F, T> List<T> copyProperties(List<F> sources, Class<T> target) {
        List<T> result = new ArrayList<>();
        if (sources == null || sources.isEmpty()) {
            return result;
        }
        sources.forEach(f -> {
            T t = null;
            try {
                t = target.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            copyProperties(f, t);
            result.add(t);
        });
        return result;
    }

}
