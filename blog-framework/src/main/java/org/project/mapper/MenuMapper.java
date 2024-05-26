package org.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.project.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-03 20:28:52
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectParmsByUserId(Long userId);

    List<Menu> selectAllMenus();

    List<Menu> selectMenusById(Long id);
}

