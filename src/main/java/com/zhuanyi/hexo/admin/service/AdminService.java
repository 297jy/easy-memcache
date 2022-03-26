package com.zhuanyi.hexo.admin.service;

import com.zhuanyi.hexo.admin.obj.form.LoginForm;
import com.zhuanyi.hexo.admin.obj.vo.AdminInfoVO;

public interface AdminService {

    String login(LoginForm loginForm);

    AdminInfoVO getAdminInfo();

    boolean logout();

}
