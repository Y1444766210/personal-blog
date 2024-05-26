package org.project.controller;

import org.project.domain.ResponseResult;
import org.project.domain.entity.LoginUser;
import org.project.domain.entity.Menu;
import org.project.domain.entity.User;
import org.project.domain.vo.AdminInfoVo;
import org.project.domain.vo.RouterVO;
import org.project.domain.vo.UserInfoVo;
import org.project.enums.AppHttpCodeEnum;
import org.project.exception.SystemException;
import org.project.service.AdminLoginService;
import org.project.service.BlogLoginService;
import org.project.service.MenuService;
import org.project.service.RoleService;
import org.project.utils.BeanCopyUtils;
import org.project.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    @GetMapping("getInfo")
    public ResponseResult getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户获得permission
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户获得roles
        List<String> roles = roleService.selectRolesByUserId(loginUser.getUser().getId());
        //封装返回
        AdminInfoVo adminInfoVo = new AdminInfoVo(perms, roles, BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class));
        return ResponseResult.okResult(adminInfoVo);
    }

    @GetMapping("getRouters")
    public ResponseResult<RouterVO> getRouters() {
        //获取menu
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long id = loginUser.getUser().getId();
        List<Menu> menuList = menuService.getMenusByUserId(id);
        //封装返回
        return ResponseResult.okResult(new RouterVO(menuList));
    }



}
