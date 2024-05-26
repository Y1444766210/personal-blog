package org.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.domain.entity.Role;
import org.project.mapper.RoleMapper;
import org.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2024-05-03 20:32:07
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    public RoleMapper roleMapper;

    @Override
    public List<String> selectRolesByUserId(Long id) {
        //判断是是否是管理员，是则只返回admin
        if(id == 1L) {
            List<String> rolesKey = new ArrayList<>();
            rolesKey.add("admin");
            return rolesKey;
        }
        //否则查询用户具有的角色信息
        return getBaseMapper().selectRolesByUserId(id);
    }
}
