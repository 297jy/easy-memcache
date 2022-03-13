package com.zhuanyi.hexo.admin.service.impl;

import com.zhuanyi.hexo.admin.form.LoginForm;
import com.zhuanyi.hexo.admin.service.AdminService;
import com.zhuanyi.hexo.admin.vo.AdminInfoVO;
import com.zhuanyi.hexo.auth.config.SystemConfig;
import com.zhuanyi.hexo.auth.service.AuthService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

@Component("defaultAdminService")
public class DefaultAdminServiceImpl implements AdminService {

    @Resource
    private AuthService defaultAuthService;

    @Resource
    private SystemConfig systemConfig;

    @Override
    public String login(LoginForm loginForm) {
        if (defaultAuthService.checkAccountInfo(loginForm.getUsername(), loginForm.getPassword())) {
            return defaultAuthService.generateToken();
        }
        return null;
    }

    @Override
    public AdminInfoVO getAdminInfo() {
        AdminInfoVO adminInfoVO = new AdminInfoVO();
        adminInfoVO.setAvatar(systemConfig.getAvatar());
        adminInfoVO.setIntroduction(systemConfig.getIntroduction());
        adminInfoVO.setName(systemConfig.getUsername());
        adminInfoVO.setRoles(Collections.singletonList("admin"));
        return adminInfoVO;
    }

    @Override
    public boolean logout() {
        return defaultAuthService.clearToken();
    }
}
