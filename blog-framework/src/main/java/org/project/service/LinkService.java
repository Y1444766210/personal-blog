package org.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.project.domain.ResponseResult;
import org.project.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-04-16 20:23:13
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

