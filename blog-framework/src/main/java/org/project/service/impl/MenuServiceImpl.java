package org.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.constants.SystemConstants;
import org.project.domain.entity.Menu;
import org.project.mapper.MenuMapper;
import org.project.service.MenuService;
import org.project.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2024-05-03 20:28:54
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public List<String> selectPermsByUserId(Long id) {
        List<String> parms = new ArrayList<>();
        //如果用户id为1，直接返回所有权限
        if(id == 1L){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            queryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);
            for (Menu menu : menus) {
                parms.add(menu.getPerms());
            }
            return parms;
        }
        //如果不为1，查询对应用户权限
        return getBaseMapper().selectParmsByUserId(id);
    }

    @Override
    public List<Menu> getMenusByUserId(Long id) {
        MenuMapper baseMapper = getBaseMapper();
        //如果用户id为1代表管理员，menus中需要有所有菜单类型为C或者M的，状态为正常的，未被删除的权限
        List<Menu> menus = null;
        if(SecurityUtils.isAdmin()) {
            menus = baseMapper.selectAllMenus();
        } else {
            //查询对应id的menus
            menus = baseMapper.selectMenusById(id);
        }
        //构建tree
        List<Menu> menuList = createTreeByMenusList(menus, 0L);
        return menuList;
    }

    private List<Menu> createTreeByMenusList(List<Menu> menus, long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    public List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childMenu = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
        return childMenu;
    }
}
