package org.project.service;

import org.project.domain.ResponseResult;
import org.project.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}