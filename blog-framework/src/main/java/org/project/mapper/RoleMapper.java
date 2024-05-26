package org.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.project.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-03 20:32:05
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRolesByUserId(Long userId);
}

