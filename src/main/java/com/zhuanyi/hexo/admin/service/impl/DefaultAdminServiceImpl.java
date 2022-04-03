package com.zhuanyi.hexo.admin.service.impl;

import com.zhuanyi.hexo.admin.dao.SystemSettingDao;
import com.zhuanyi.hexo.admin.obj.dto.SystemSettingDTO;
import com.zhuanyi.hexo.admin.obj.form.LoginForm;
import com.zhuanyi.hexo.admin.obj.vo.SystemSettingVO;
import com.zhuanyi.hexo.admin.service.AdminService;
import com.zhuanyi.hexo.admin.obj.vo.AdminInfoVO;
import com.zhuanyi.hexo.admin.config.SystemConfig;
import com.zhuanyi.hexo.auth.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

@Component("defaultAdminService")
public class DefaultAdminServiceImpl implements AdminService {

    @Resource
    private AuthService defaultAuthService;

    @Resource
    private SystemSettingDao defaultSystemSettingDao;

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

    @Override
    public SystemSettingVO getSystemSetting() {
        SystemSettingVO systemSettingVO = new SystemSettingVO();
        BeanUtils.copyProperties(systemConfig, systemSettingVO);
        return systemSettingVO;
    }

    @Override
    public boolean updateSystemSetting(SystemSettingDTO systemSettingDTO) {
        return defaultSystemSettingDao.updateSystemSetting(systemSettingDTO);
    }
}
