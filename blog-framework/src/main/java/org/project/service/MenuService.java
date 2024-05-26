package org.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.project.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2024-05-03 20:28:54
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> getMenusByUserId(Long id);
}

