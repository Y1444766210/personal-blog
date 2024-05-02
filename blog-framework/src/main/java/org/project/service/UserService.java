package org.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.project.domain.ResponseResult;
import org.project.domain.entity.User;
import org.project.domain.vo.UserInfoVo;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-04-23 17:17:48
 */
public interface UserService extends IService<User> {

    ResponseResult getUserInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

