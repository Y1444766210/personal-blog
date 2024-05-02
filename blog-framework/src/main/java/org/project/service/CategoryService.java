package org.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.project.domain.ResponseResult;
import org.project.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-04-14 17:56:18
 */

public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

