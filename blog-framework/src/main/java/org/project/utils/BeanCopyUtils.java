package org.project.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){}

    public static <V> V copyBean(Object o, Class<V> clazz){
        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(o, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz) throws InstantiationException, IllegalAccessException {
        List<V> result = new ArrayList<>();
        for (O o : list) {
            V v = copyBean(o, clazz);
            result.add(v);
        }
        /*return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());*/
        return result;
    }
}
