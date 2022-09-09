package org.khasanof.graphql.utils;

import org.khasanof.graphql.dto.blog.BlogCreateDTO;
import org.khasanof.graphql.dto.blog.BlogUpdateDTO;
import org.khasanof.graphql.entity.Blog;
import org.springframework.beans.BeanUtils;

public class BaseUtils {

    public static Blog dtoToBlog(BlogCreateDTO dto, Blog obj) {
        BeanUtils.copyProperties(dto, obj);
        return obj;
    }

    public static Blog dtoToBlog(BlogUpdateDTO dto, Blog obj) {
        BeanUtils.copyProperties(dto, obj);
        return obj;
    }

    public static Blog dtoToBlog(BlogCreateDTO dto, Blog obj, String... ignores) {
        BeanUtils.copyProperties(dto, obj, ignores);
        return obj;
    }
}
