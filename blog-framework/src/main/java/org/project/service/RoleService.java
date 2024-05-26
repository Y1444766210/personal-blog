package org.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.project.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2024-05-03 20:32:07
 */
public interface RoleService extends IService<Role> {

    List<String> selectRolesByUserId(Long id);
}

